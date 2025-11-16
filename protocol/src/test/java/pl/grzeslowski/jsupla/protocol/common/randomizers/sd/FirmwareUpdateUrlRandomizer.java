package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class FirmwareUpdateUrlRandomizer implements Randomizer<FirmwareUpdateUrl> {
    private final RandomSupla randomSupla;

    public FirmwareUpdateUrlRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public FirmwareUpdateUrl getRandomValue() {
        return new FirmwareUpdateUrl(
                randomSupla.nextByte(),
                randomSupla.nextByteArrayFromString(SUPLA_URL_HOST_MAXSIZE),
                randomSupla.nextInt(65_535),
                randomSupla.nextByteArrayFromString(SUPLA_URL_PATH_MAXSIZE));
    }
}
