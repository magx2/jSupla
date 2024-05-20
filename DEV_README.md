# Deploying to Maven Central

1. Run [Release with Gradle](https://github.com/magx2/jSupla/actions/workflows/release.yml) action
2. Go to [sonatype](https://s01.oss.sonatype.org/#stagingRepositories) and select `Staging Repositories`
3. Select `plgrzeslowski-100X` and click `close` and after `release`
4. Artifact should be visible after some time

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