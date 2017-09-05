package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class FirmwareUpdateParamsRandomizer implements Randomizer<SuplaFirmwareUpdateParams> {
    private final RandomBean randomBean;

    public FirmwareUpdateParamsRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaFirmwareUpdateParams getRandomValue() {
        return new SuplaFirmwareUpdateParams(
                                               randomBean.nextByte(),
                                               randomBean.nextPositiveInt(),
                                               randomBean.nextPositiveInt(),
                                               randomBean.nextPositiveInt(),
                                               randomBean.nextPositiveInt()
        );
    }
}
