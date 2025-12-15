# AGENTS.md

AI agent guide for **jSupla** - a Java implementation of the Supla IoT protocol.

## 1. Project Overview

**jSupla** is a Java 21 implementation of the Supla IoT protocol, enabling communication between smart devices in a connected environment. The project consists of two main modules:

- **protocol**: Data structures, encoders/decoders, and protocol helpers for Supla messages. Includes extensive code generation from protocol definitions.
- **server**: Netty-based server implementation using Project Reactor for asynchronous, reactive communication with Supla devices and clients.

The project is published to Maven Central under `pl.grzeslowski.jSupla` and follows professional software engineering practices with automated builds, testing, and releases.

## 2. Repository Structure

```
jSupla/
├── protocol/                           # Protocol module
│   ├── src/main/java/                 # Manual protocol code
│   ├── src/test/java/                 # Unit tests
│   ├── src/integrationTest/java/      # Integration tests
│   ├── build/generated/sources/       # GENERATED CODE (DO NOT EDIT)
│   ├── build.gradle                   # Module build config
│   ├── parser.gradle                  # Protocol parser
│   ├── pojoGenerator.gradle           # POJO generator
│   ├── decoders.gradle                # Decoder generator
│   └── encoders.gradle                # Encoder generator
│
├── server/                             # Server module
│   ├── src/main/java/                 # Netty/Reactor server
│   ├── src/test/java/                 # Unit tests
│   ├── src/integrationTest/java/      # Integration tests
│   └── build.gradle                   # Module build config
│
├── config/                             # Checkstyle configuration
├── formatters/                         # IntelliJ code style
├── docs/                               # Release notes
├── .github/workflows/                  # CI/CD pipelines
├── build.gradle                        # Root build config
├── settings.gradle                     # Multi-module settings
└── gradle.properties                   # Version: 4.1.1-SNAPSHOT
```

**Key Directories:**
- Generated code: `protocol/build/generated/sources/jsupla/main/java/` (NEVER EDIT)
- Manual protocol code: `protocol/src/main/java/pl/grzeslowski/jsupla/protocol/api/`
- Server code: `server/src/main/java/pl/grzeslowski/jsupla/server/`

## 3. Build & Run Instructions

**Prerequisites:**
- Java 21 (Gradle toolchain will download if needed)
- Gradle 8.10.2 (use included wrapper: `./gradlew`)

**Build Commands:**
```bash
# Full build (all modules, tests, formatting)
./gradlew build

# Clean build
./gradlew clean build

# Build specific module
./gradlew :protocol:build
./gradlew :server:build

# Verification (tests + integration tests + formatting)
./gradlew check

# Code formatting (REQUIRED before commits)
./gradlew spotlessApply

# Install to local Maven repository
./gradlew publishToMavenLocal
```

**CRITICAL:** Always run `./gradlew spotlessApply` before committing. This formats code AND runs code generators in the protocol module.

## 4. Testing Instructions

**Test Types:**
- **Unit tests**: `src/test/java/` - Test individual classes in isolation
- **Integration tests**: `src/integrationTest/java/` - Test complete flows (especially Netty/Reactor)

**Run Tests:**
```bash
# All tests (unit + integration)
./gradlew check

# Unit tests only
./gradlew test

# Integration tests only
./gradlew integrationTest

# Module-specific tests
./gradlew :protocol:test
./gradlew :server:integrationTest
```

**Test Frameworks:**
- JUnit 4.12
- AssertJ 3.8.0 (fluent assertions)
- Mockito 1.10.19 (mocking)
- Random Beans 3.9.0 (test data generation)
- EqualsVerifier 3.3 (equals/hashCode validation)

**Test Conventions:**
- File naming: `*Test.java`
- Assertion style: `assertThat(actual).isEqualTo(expected)`
- Test method naming: Descriptive with "should" (e.g., `decimalValueShouldExposeBigDecimal`)
- Use existing test utilities: Randomizers for test data, EqualsVerifier for value objects

## 5. Coding Standards & Conventions

**Formatting:**
- Google Java Format (AOSP style) via Spotless plugin
- Line length: max 120 characters
- Unix (LF) line endings
- Java indentation: 4 spaces (NEVER tabs)
- YAML indentation: 2 spaces
- UTF-8 encoding

**Naming Conventions:**
- Classes: PascalCase (e.g., `SuplaRegisterDevice`)
- Methods: camelCase (e.g., `getChannelValue`)
- Variables: camelCase (e.g., `resultCode`)
- Constants: UPPER_SNAKE_CASE (e.g., `SUPLA_DCS_CALL_PING_SERVER`)
- Test classes: `*Test.java` suffix
- Interfaces: Simple names (e.g., `MessageHandler`, `ToClientProto`)
- Implementations: Often `*Impl` suffix (e.g., `DecoderFactoryImpl`)

**Code Patterns:**
- Use Lombok for reducing boilerplate: `@ToString`, `val`, `@RequiredArgsConstructor`
- Prefer `val` for final local variables
- SLF4J for logging: `LoggerFactory.getLogger(ClassName.class.getName())`
- Use `requireNonNull` for null checks
- Static imports for utilities and constants
- Fluent AssertJ assertions in tests

**Package Structure:**
- Base: `pl.grzeslowski.jsupla`
- Protocol: `pl.grzeslowski.jsupla.protocol.api`
- Server: `pl.grzeslowski.jsupla.server`
- Protocol directions: `cs` (Client→Server), `sc` (Server→Client), `ds` (Device→Server), `sd` (Server→Device), etc.

## 6. Dependencies & Tooling

**Core Dependencies:**
- Java 21 (toolchain)
- Gradle 8.10.2 (wrapper)
- Netty 4.2.7.Final (networking)
- Project Reactor 3.6.2 (reactive streams)
- Lombok 1.18.32 (code generation)
- SLF4J 2.0.11 (logging)

**Test Dependencies:**
- JUnit 4.12
- AssertJ 3.8.0
- Mockito 1.10.19
- Random Beans 3.9.0
- EqualsVerifier 3.3

**Build Tools:**
- Spotless 7.0.4 (code formatting)
- Checkstyle (Google Java Style Guide)
- Nexus Publishing Plugin (Maven Central)
- GPG signing for artifacts

**Code Generation (Protocol Module):**
- Protocol parser: Generates POJOs from Supla C protocol definitions
- Encoder/Decoder generators: Creates serialization code
- Call type generator: Generates protocol constants
- Runs automatically during `spotlessApply`

## 7. Rules for AI Agents

### DOs:

- ✅ **ALWAYS** run `./gradlew spotlessApply` before committing
- ✅ **ALWAYS** read existing code before proposing changes
- ✅ **ALWAYS** run `./gradlew check` before considering work complete
- ✅ Use AssertJ for assertions: `assertThat(actual).isEqualTo(expected)`
- ✅ Follow existing patterns in the module you're editing
- ✅ Add tests for new functionality (unit + integration where appropriate)
- ✅ Use Lombok annotations to reduce boilerplate
- ✅ Use SLF4J for logging: `LoggerFactory.getLogger(ClassName.class.getName())`
- ✅ Respect the module boundaries: protocol vs. server
- ✅ Use 4 spaces for Java indentation
- ✅ Keep lines under 120 characters
- ✅ Use descriptive test method names with "should"

### DON'Ts:

- ❌ **NEVER** edit generated code in `protocol/build/generated/sources/jsupla/main/java/`
- ❌ **NEVER** use tabs for Java indentation (use 4 spaces)
- ❌ **NEVER** skip `spotlessApply` (it runs code generators)
- ❌ **NEVER** commit without running `./gradlew check`
- ❌ **NEVER** modify protocol structures without understanding code generation
- ❌ **NEVER** add dependencies without clear justification
- ❌ **NEVER** create new modules without discussion
- ❌ **NEVER** change public APIs without considering backward compatibility
- ❌ **NEVER** skip tests for new functionality
- ❌ **NEVER** ignore Checkstyle or Spotless violations

## 8. Safe Modification Guidelines

### Protocol Module (`protocol/`)

**Generated Code:**
- Location: `protocol/build/generated/sources/jsupla/main/java/`
- DO NOT edit generated files directly
- Generators: `parser.gradle`, `pojoGenerator.gradle`, `consts.gradle`, `decoders.gradle`, `encoders.gradle`
- To modify: Update generator scripts or protocol definitions, then run `./gradlew spotlessApply`

**Manual Code:**
- Location: `protocol/src/main/java/`
- Interfaces and abstractions for protocol types
- Channel type decoders/encoders
- Value objects and domain models
- Safe to edit directly

**When to Use Each:**
- Generated: Protocol structures that match wire format (e.g., `SuplaRegisterDevice`)
- Manual: Business logic, type conversions, channel-specific logic (e.g., `HvacValue`)

### Server Module (`server/`)

**All Code is Manual:**
- No code generation
- Uses Netty for networking
- Uses Project Reactor for reactive streams
- Safe to edit directly

**Key Entry Points:**
- `NettyServer.java` - Server initialization
- `NettyServerInitializer.java` - Channel pipeline setup
- `SuplaHandler.java` - Protocol handler
- `MessageHandler.java` - Message processing
- `SuplaDataPacketEncoder.java` / `SuplaDataPacketDecoder.java` - Codecs

**Modification Pattern:**
1. Understand the Netty channel pipeline flow
2. Follow reactive patterns (return `Mono` or `Flux`)
3. Test with integration tests (full Netty flow)

### Testing Strategy

**Unit Tests:**
- Test single classes in isolation
- Mock dependencies with Mockito
- Use Random Beans for test data generation
- Fast, no external dependencies

**Integration Tests:**
- Test complete flows
- Use when testing Netty channel pipeline
- Use when testing Reactor operators
- Test encoder/decoder pairs together

**Test Utilities:**
- `*Randomizer` classes: Generate random test data
- `EqualsVerifier`: Validate equals/hashCode contracts
- AssertJ: Fluent assertions

## 9. What to Ask Before Making Changes

### Protocol Changes:
- "Should I update the code generator or create manual code?"
- "Does this change affect wire format compatibility?"
- "Is this a new protocol message or modification to existing?"
- "Should this be in the protocol module or server module?"

### Architecture Decisions:
- "Should this use reactive streams (Reactor) or be synchronous?"
- "Does this require a new dependency? What's the justification?"
- "How does this fit into the Netty channel pipeline?"
- "Should this be a separate class or extend existing functionality?"

### Breaking Changes:
- "This changes public API - should I deprecate the old method first?"
- "This affects Maven Central published artifacts - is this acceptable?"
- "Should I update the version number (in gradle.properties)?"
- "Are there users depending on this behavior?"

### Testing Strategy:
- "Should this have unit tests, integration tests, or both?"
- "Are there existing test patterns I should follow?"
- "Should I update existing tests or create new ones?"
- "What edge cases should I test?"

### Documentation:
- "Should I update README.md with this change?"
- "Does this require release notes (in docs/release-notes.md)?"
- "Should I add Javadoc for this public API?"
- "Is this a breaking change that needs migration guide?"

### Code Generation:
- "If modifying protocol structures, which generator needs updating?"
- "Do I need to update protocol definitions or just generators?"
- "Will this affect existing generated code?"

## Summary

**jSupla** is a professionally maintained Java 21 project with:
- Multi-module Gradle build
- Extensive code generation (protocol module)
- Reactive programming with Netty and Reactor
- Automated formatting and testing
- Maven Central publishing

**Key Rules for AI Agents:**
1. Always run `./gradlew spotlessApply` before commits
2. Never edit generated code
3. Follow existing patterns
4. Add tests for new functionality
5. Ask questions when unsure

For more details, see:
- `README.md` - User-facing documentation
- `DEV_README.md` - Deployment and release process
- `build.gradle` - Build configuration
- `protocol/build.gradle` - Code generation setup
