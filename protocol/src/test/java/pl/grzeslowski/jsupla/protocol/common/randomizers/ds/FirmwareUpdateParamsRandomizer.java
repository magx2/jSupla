package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class FirmwareUpdateParamsRandomizer implements Randomizer<FirmwareUpdateParams> {
    private final RandomSupla randomSupla;

    public FirmwareUpdateParamsRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public FirmwareUpdateParams getRandomValue() {
        return new FirmwareUpdateParams(
                randomSupla.nextByte(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextPositiveInt());
    }
}
