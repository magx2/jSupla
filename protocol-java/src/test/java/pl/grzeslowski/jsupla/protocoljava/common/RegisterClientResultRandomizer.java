package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

class RegisterClientResultRandomizer implements Randomizer<RegisterClientResult> {
    private final EnhancedRandom random;

    RegisterClientResultRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public RegisterClientResult getRandomValue() {
        final short version = (short) (random.nextInt(UNSIGNED_BYTE_MAX) + 1);
        final short versionMin = (short) random.nextInt(version);
        return new RegisterClientResult(
                                               random.nextInt(1000),
                                               random.nextInt(1000),
                                               random.nextInt(1000),
                                               random.nextInt(1000),
                                               (short) random.nextInt(UNSIGNED_BYTE_MAX),
                                               version,
                                               versionMin
        );
    }
}
