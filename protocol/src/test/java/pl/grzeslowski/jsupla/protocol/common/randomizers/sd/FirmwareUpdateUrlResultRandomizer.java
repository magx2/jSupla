package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class FirmwareUpdateUrlResultRandomizer implements Randomizer<SuplaFirmwareUpdateUrlResult> {
    private final RandomBean randomBean;

    public FirmwareUpdateUrlResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaFirmwareUpdateUrlResult getRandomValue() {
        return new SuplaFirmwareUpdateUrlResult(
                                                  randomBean.nextByte(),
                                                  randomBean.nextObject(SuplaFirmwareUpdateUrl.class)
        );
    }
}
