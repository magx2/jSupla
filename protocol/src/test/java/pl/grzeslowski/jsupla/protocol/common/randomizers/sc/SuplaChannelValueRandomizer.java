package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelValueRandomizer implements Randomizer<SuplaChannelValue> {
    private final RandomSupla randomSupla;

    public SuplaChannelValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelValue getRandomValue() {
        return new SuplaChannelValue(
                                            randomSupla.nextByte(),
                                            randomSupla.nextPositiveInt(),
                                            randomSupla.nextByte(),
                                            randomSupla.nextObject(
                                                    pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class)
        );
    }
}
