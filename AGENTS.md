# AI Contributor Guide

Welcome to **jSupla**, a Java 21 implementation of the Supla protocol that is split into a protocol library and a Netty/Reactor-based server. Use this document as the default instruction set for any AI-assisted edits in this repository.

## Repository layout
- `protocol`: Supla data structures, encoders/decoders, and generated protocol helpers.
- `server`: Netty/Reactor server implementation that consumes the protocol module.
- `formatters`, `config`, and Gradle build scripts contain shared build/formatting logic.

## Coding conventions
- Java code targets **Java 21**; prefer using the Gradle toolchain settings instead of hardcoding Java versions.
- Follow the existing SLF4J logging patterns and Lombok annotations already used throughout the project.
- Protocol code uses generated sources under `protocol/build/generated/sources/jsupla/main/java`; do **not** edit generated files directly—update the generators or inputs instead.

## Build, formatting, and tests
- Use the Gradle wrapper for all tasks: `./gradlew <task>`.
- Default verification is `./gradlew check`, which runs unit tests and integration tests.
- Run formatting before commits with `./gradlew spotlessApply`; formatting in `protocol` also triggers the code generator tasks that populate generated sources.
- If you only need module-specific tests, you can run `./gradlew :protocol:test` or `./gradlew :server:test`.

## Pull request expectations
- Keep changes focused and describe affected modules (protocol or server) clearly.
- Update or add tests when modifying behavior. Prefer integration tests when touching Netty/Reactor flows, and unit tests for protocol structures or codecs.

## Documentation changes
- When adding or updating docs, prefer Markdown and keep instructions actionable. Reflect new build or testing requirements if they change.
