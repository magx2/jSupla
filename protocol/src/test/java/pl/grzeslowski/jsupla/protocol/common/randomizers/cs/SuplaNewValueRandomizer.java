package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaNewValueRandomizer implements Randomizer<SuplaNewValue> {
    private final RandomSupla randomSupla;

    public SuplaNewValueRandomizer(RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaNewValue getRandomValue() {
        return new SuplaNewValue(
                randomSupla.nextPositiveInt(),
                randomSupla.nextByte(),
                randomSupla.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
