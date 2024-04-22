package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;

class RegisterDeviceResultRandomizer implements Randomizer<RegisterDeviceResult> {
    private final EnhancedRandom random;

    RegisterDeviceResultRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public RegisterDeviceResult getRandomValue() {
        final int version = random.nextInt(Byte.MAX_VALUE) + 1;
        final int versionMin = random.nextInt(version);
        return new RegisterDeviceResult(
            random.nextInt(100),
            random.nextInt(Byte.MAX_VALUE),
            version,
            versionMin
        );
    }
}
