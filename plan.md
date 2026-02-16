# Server Netty Hardening Plan

## Scope
This plan addresses all findings from the `server` review:
1. Startup bind failure handling.
2. Shutdown ordering and timeout safety.
3. Logger memory growth from dynamic logger names.
4. Per-channel delimiter allocation churn.
5. Explicit boss event-loop sizing.

The goal is to make lifecycle behavior deterministic and reduce long-running memory/thread risk without changing public protocol behavior.

## Phase 0: Baseline and Guardrails
### Objective
Capture current behavior before code changes.

### Actions
- [x] Confirm working branch is `review/server-netty-leak-audit`.
- [x] Capture current file status:
- `git -c safe.directory=D:/repos/jSupla status --short`
- [x] Re-run baseline verification (already green, but record again when implementing):
- `./gradlew :server:test`
- `./gradlew :server:check`

### Exit Criteria
- Current branch and clean scope of change are known.
- Baseline tests are green and logged.

---

## Phase 1: Fix Startup Bind Semantics (High)
### Problem
`NettyServer` creates event-loop groups and calls async `bind()` without waiting for success. Constructor can return with startup failure not surfaced, and threads may live longer than expected.

### Files
- `server/src/main/java/pl/grzeslowski/jsupla/server/NettyServer.java`

### Actions
- [x] Change startup flow to await bind completion before constructor returns.
- [x] Validate bind result (`isSuccess` / `cause`).
- [x] On bind failure:
- [x] Shutdown `bossGroup` and `workerGroup` immediately.
- [x] Throw a meaningful exception (include port + cause).
- [x] Ensure `channelFuture` is assigned only on successful bind.

### Acceptance Criteria
- Constructor fails fast on occupied/invalid port.
- No event-loop groups remain active after failed startup path.

### Suggested Test Coverage
- [x] Add/extend test that occupies a port and verifies `NettyServer` constructor throws.
- [x] Verify exception contains clear startup context.

---

## Phase 2: Fix Shutdown Lifecycle (Medium)
### Problem
Current close order shuts down event loops first and waits on `closeFuture` later, which is brittle and can hang.

### Files
- `server/src/main/java/pl/grzeslowski/jsupla/server/NettyServer.java`

### Actions
- [x] Update `close()` order:
1. [ ] Close server channel explicitly.
2. [ ] Await channel close completion.
3. [ ] Shutdown worker group gracefully.
4. [ ] Shutdown boss group gracefully.
- [x] Add bounded waits/timeouts for shutdown futures.
- [x] Add warning logs for timeout/failure conditions.
- [x] Make close idempotent (safe to call multiple times).

### Acceptance Criteria
- `close()` reliably completes within bounded time under normal conditions.
- No indefinite blocking on `closeFuture`.
- Repeated `close()` does not throw or re-shutdown unsafely.

### Suggested Test Coverage
- [x] Add test that starts server and calls `close()` twice.
- [x] Add test asserting close returns (not hanging) within reasonable timeout.

---

## Phase 3: Remove Logger Name Explosion (Medium/High in long-lived systems)
### Problem
Per-instance logger names include unique IDs, potentially creating unbounded logger cache growth in logging backends.

### Files
- `server/src/main/java/pl/grzeslowski/jsupla/server/NettyServer.java`
- `server/src/main/java/pl/grzeslowski/jsupla/server/NettyServerInitializer.java`
- `server/src/main/java/pl/grzeslowski/jsupla/server/SuplaHandler.java`
- `server/src/main/java/pl/grzeslowski/jsupla/server/SuplaDataPacketDecoder.java`
- `server/src/main/java/pl/grzeslowski/jsupla/server/SuplaDataPacketEncoder.java`
- `server/src/main/java/pl/grzeslowski/jsupla/server/SuplaDefaultWriter.java`

### Actions
- [x] Replace dynamic logger creation with static class-level loggers.
- [x] Keep connection correlation by logging existing `uuid` as log parameter instead of logger name.
- [x] Remove now-unused logger-ID counters/imports.

### Acceptance Criteria
- Logger names are stable per class.
- Per-connection logs still include correlation ID in message arguments.

### Suggested Test Coverage
- [x] No dedicated unit test required; validate by code inspection + runtime logs.

---

## Phase 4: Reuse Delimiter Buffer in Pipeline (Low/Medium)
### Problem
`Unpooled.copiedBuffer(SUPLA_TAG)` is allocated per channel.

### Files
- `server/src/main/java/pl/grzeslowski/jsupla/server/NettyServerInitializer.java`

### Actions
- [x] Introduce one static shared delimiter buffer.
- [x] Use read-only/unreleasable semantics and duplicate/slice per pipeline insertion as needed.
- [x] Confirm compatibility with `DelimiterBasedFrameDecoder` constructor.

### Acceptance Criteria
- No per-channel heap buffer copy for static delimiter.
- Behavior unchanged for frame decoding.

### Suggested Test Coverage
- [x] Existing tests should continue passing.
- [x] Optional targeted decoder framing test if future regressions occur.

---

## Phase 5: Explicit Boss Event-Loop Size (Low)
### Problem
Boss group uses default sizing; for acceptor thread this is usually unnecessary overhead.

### Files
- `server/src/main/java/pl/grzeslowski/jsupla/server/NettyServer.java`

### Actions
- [x] Set boss group to explicit size `1`.
- [x] Keep worker group default unless there is a config requirement.
- [x] Add short code comment explaining why boss is fixed.

### Acceptance Criteria
- Boss group is deterministic and minimal.
- No functional behavior change in tests.

---

## Phase 6: Tests and Validation
### Objective
Verify correctness and catch regressions around lifecycle and leak safety.

### Actions
- [x] Run formatting/generation:
- `./gradlew spotlessApply`
- [x] Run server verification:
- `./gradlew :server:test --rerun-tasks`
- `./gradlew :server:check --rerun-tasks`
- [x] Run paranoid leak detection:
- PowerShell-safe command:
	- `.\gradlew --% -Dio.netty.leakDetection.level=paranoid :server:test --rerun-tasks`
- [x] (Optional) Run with warnings for deprecations:
- `./gradlew :server:compileJava --warning-mode all`

### Acceptance Criteria
- All server tests/check tasks pass.
- No Netty leak detector warnings in test output.
- No Spotless violations.

---

## Phase 7: Review and Delivery
### Actions
- [x] Generate concise diff summary:
- `git -c safe.directory=D:/repos/jSupla diff -- server/src/main/java server/src/test/java`
- [x] Verify no generated files were manually edited.
- [x] Provide final report with:
- [x] What changed per issue.
- [x] Test commands executed and results.
- [x] Remaining risks (if any).

### Exit Criteria
- All five issues are addressed in code.
- Validation is reproducible via commands above.
- Changes are review-ready on branch `review/server-netty-leak-audit`.

---

## Implementation Notes and Risk Management
- Keep API compatibility for `NettyServer` unless a constructor signature change is explicitly approved.
- Avoid behavior changes in protocol encoding/decoding logic.
- If any lifecycle refactor increases complexity, prefer small helper methods with clear responsibilities:
- `bindAndValidate(...)`
- `closeChannel(...)`
- `shutdownGroup(...)`
- If timeout values are introduced, keep them as constants with explicit units.

## Definition of Done
- [x] All plan phases completed.
- [x] `spotlessApply` and `:server:check` pass.
- [x] No new warnings/errors introduced in server tests.
- [x] Final review confirms thread/resource cleanup is deterministic and safer than baseline.
