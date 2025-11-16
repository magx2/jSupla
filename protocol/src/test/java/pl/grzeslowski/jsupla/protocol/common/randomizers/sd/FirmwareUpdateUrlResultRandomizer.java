package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class FirmwareUpdateUrlResultRandomizer implements Randomizer<FirmwareUpdateUrlResult> {
    private final RandomSupla randomSupla;

    public FirmwareUpdateUrlResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public FirmwareUpdateUrlResult getRandomValue() {
        return new FirmwareUpdateUrlResult(
                randomSupla.nextByte((byte) 1), randomSupla.nextObject(FirmwareUpdateUrl.class));
    }
}
