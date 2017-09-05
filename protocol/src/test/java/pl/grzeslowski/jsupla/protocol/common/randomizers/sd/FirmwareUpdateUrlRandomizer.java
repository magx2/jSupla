package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public class FirmwareUpdateUrlRandomizer implements Randomizer<SuplaFirmwareUpdateUrl> {
    private final RandomBean randomBean;

    public FirmwareUpdateUrlRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaFirmwareUpdateUrl getRandomValue() {
        return new SuplaFirmwareUpdateUrl(
                                            randomBean.nextByte(),
                                            randomBean.nextByteArray(SUPLA_URL_HOST_MAXSIZE),
                                            randomBean.nextPositiveInt(),
                                            randomBean.nextByteArray(SUPLA_URL_PATH_MAXSIZE)
        );
    }
}
