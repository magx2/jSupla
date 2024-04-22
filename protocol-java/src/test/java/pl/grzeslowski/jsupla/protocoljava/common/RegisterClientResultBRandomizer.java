package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

class RegisterClientResultBRandomizer implements Randomizer<RegisterClientResultB> {
    private final EnhancedRandom random;

    RegisterClientResultBRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public RegisterClientResultB getRandomValue() {
        final short version = (short) (random.nextInt(UNSIGNED_BYTE_MAX) + 1);
        final short versionMin = (short) random.nextInt(version);
        return new RegisterClientResultB(
            random.nextInt(1000),
            random.nextInt(1000),
            random.nextInt(1000),
            random.nextInt(1000),
            (short) random.nextInt(UNSIGNED_BYTE_MAX),
            version,
            versionMin,
            random.nextInt()
        );
    }
}
