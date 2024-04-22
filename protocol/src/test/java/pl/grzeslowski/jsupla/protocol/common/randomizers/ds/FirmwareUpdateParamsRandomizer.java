package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class FirmwareUpdateParamsRandomizer implements Randomizer<SuplaFirmwareUpdateParams> {
    private final RandomSupla randomSupla;

    public FirmwareUpdateParamsRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaFirmwareUpdateParams getRandomValue() {
        return new SuplaFirmwareUpdateParams(
            randomSupla.nextByte(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextPositiveInt()
        );
    }
}
