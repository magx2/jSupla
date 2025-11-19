package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValueA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelValueRandomizer implements Randomizer<SuplaChannelValue> {
    private final RandomSupla randomSupla;

    public SuplaChannelValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelValue getRandomValue() {
        return new SuplaChannelValueA(
                randomSupla.nextByte(),
                randomSupla.nextPositiveInt(),
                randomSupla.nextByte(),
                null);
    }
}
