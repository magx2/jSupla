package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class FirmwareUpdateUrlResultRandomizer implements Randomizer<FirmwareUpdateUrlResult> {
    private final RandomBean randomBean;

    public FirmwareUpdateUrlResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public FirmwareUpdateUrlResult getRandomValue() {
        return new FirmwareUpdateUrlResult(
                                                  randomBean.nextByte(),
                                                  randomBean.nextObject(FirmwareUpdateUrl.class)
        );
    }
}
