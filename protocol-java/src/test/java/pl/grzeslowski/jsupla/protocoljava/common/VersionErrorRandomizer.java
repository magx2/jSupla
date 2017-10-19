package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class VersionErrorRandomizer implements Randomizer<VersionError> {
    private final EnhancedRandom random;

    public VersionErrorRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public VersionError getRandomValue() {
        final int serverVersion = random.nextInt(UNSIGNED_BYTE_MAX);
        final int serverVersionMin = random.nextInt(serverVersion);
        return new VersionError(serverVersionMin, serverVersion);
    }
}
