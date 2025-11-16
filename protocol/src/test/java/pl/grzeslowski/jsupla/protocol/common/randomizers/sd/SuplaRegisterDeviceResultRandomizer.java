package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaRegisterDeviceResultRandomizer implements Randomizer<SuplaRegisterDeviceResult> {
    private final RandomSupla randomSupla;

    public SuplaRegisterDeviceResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterDeviceResult getRandomValue() {
        final byte version = (byte) (randomSupla.nextPositiveByte() + 1);
        return new SuplaRegisterDeviceResult(
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveByte(),
                version,
                randomSupla.nextByte(version));
    }
}
