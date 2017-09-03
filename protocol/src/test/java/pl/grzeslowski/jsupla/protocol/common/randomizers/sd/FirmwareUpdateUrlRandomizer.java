package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public class FirmwareUpdateUrlRandomizer implements Randomizer<FirmwareUpdateUrl> {
    private final RandomBean randomBean;

    public FirmwareUpdateUrlRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public FirmwareUpdateUrl getRandomValue() {
        return new FirmwareUpdateUrl(
                                            randomBean.nextByte(),
                                            randomBean.nextByteArray(SUPLA_URL_HOST_MAXSIZE),
                                            randomBean.nextPositiveInt(),
                                            randomBean.nextByteArray(SUPLA_URL_PATH_MAXSIZE)
        );
    }
}
