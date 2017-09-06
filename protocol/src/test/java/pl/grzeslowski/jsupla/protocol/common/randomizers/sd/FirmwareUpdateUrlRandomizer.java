package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public class FirmwareUpdateUrlRandomizer implements Randomizer<SuplaFirmwareUpdateUrl> {
    private final RandomSupla randomSupla;

    public FirmwareUpdateUrlRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaFirmwareUpdateUrl getRandomValue() {
        return new SuplaFirmwareUpdateUrl(
                                                 randomSupla.nextByte(),
                                                 randomSupla.nextByteArray(SUPLA_URL_HOST_MAXSIZE),
                                                 randomSupla.nextPositiveInt(),
                                                 randomSupla.nextByteArray(SUPLA_URL_PATH_MAXSIZE)
        );
    }
}
