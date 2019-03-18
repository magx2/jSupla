package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelNewValueResultRandomizer implements Randomizer<SuplaChannelNewValueResult> {
    private final RandomSupla randomSupla;

    public SuplaChannelNewValueResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelNewValueResult getRandomValue() {
        return new SuplaChannelNewValueResult(
            randomSupla.nextUnsignedByte(),
            randomSupla.nextPositiveInt(),
            randomSupla.nextBoolByte());
    }
}
