package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class FirmwareUpdateParamsRandomizer implements Randomizer<FirmwareUpdateParams> {
    private final RandomBean randomBean;

    public FirmwareUpdateParamsRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public FirmwareUpdateParams getRandomValue() {
        return new FirmwareUpdateParams(
                                               randomBean.nextByte(),
                                               randomBean.nextInt(),
                                               randomBean.nextInt(),
                                               randomBean.nextInt(),
                                               randomBean.nextInt()
        );
    }
}
