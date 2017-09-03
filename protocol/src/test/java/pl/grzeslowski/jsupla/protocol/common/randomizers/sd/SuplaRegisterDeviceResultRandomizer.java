package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaRegisterDeviceResultRandomizer implements Randomizer<SuplaRegisterDeviceResult> {
    private final RandomBean randomBean;

    public SuplaRegisterDeviceResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaRegisterDeviceResult getRandomValue() {
        final byte version = randomBean.nextByte();
        return new SuplaRegisterDeviceResult(
                                                    randomBean.nextInt(),
                                                    randomBean.nextByte(),
                                                    version,
                                                    randomBean.nextByte(version)
        );
    }
}
