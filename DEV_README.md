# Deploying to Maven Central

The release process is automated via the [Release with Gradle](https://github.com/magx2/jSupla/actions/workflows/release.yml) GitHub Action. Once the workflow completes successfully, the artifacts will be automatically released to Maven Central.

You can verify the release status and find the artifacts on [Maven Central](https://search.maven.org/search?q=g:pl.grzeslowski.jSupla).

## Prerequisites

In `~/.gradle/gradle.properties` add this values:

```properties
// https://central.sonatype.org/publish/publish-gradle/#credentials
signing.keyId=<Short GPG Key - 8 chars>
signing.password=<GPG Key Pass>
signing.secretKeyRingFile=<GPG File>
ossrhUsername=<username>
ossrhPassword=<password>
```

## Links

1. [Releasing Deployment from OSSRH to the Central Repository](https://central.sonatype.org/publish/release/)
2. [Deploying to OSSRH with Gradle](https://central.sonatype.org/publish/publish-gradle/)
3. [OSSRH: Various projects under "pl.grzeslowski"](https://issues.sonatype.org/projects/OSSRH/issues/OSSRH-98249?filter=reportedbyme)
4. [Sonatype Nexus Repository REST API](https://central.sonatype.com/api-doc)