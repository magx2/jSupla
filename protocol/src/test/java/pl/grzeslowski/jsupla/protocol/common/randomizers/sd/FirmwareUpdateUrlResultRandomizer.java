package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class FirmwareUpdateUrlResultRandomizer implements Randomizer<SuplaFirmwareUpdateUrlResult> {
    private final RandomSupla randomSupla;

    public FirmwareUpdateUrlResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaFirmwareUpdateUrlResult getRandomValue() {
        return new SuplaFirmwareUpdateUrlResult(
                                                       randomSupla.nextByte(),
                                                       randomSupla.nextObject(SuplaFirmwareUpdateUrl.class)
        );
    }
}
