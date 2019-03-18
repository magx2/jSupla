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
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaNewValue(
                randomSupla.nextPositiveInt(),
                randomSupla.nextByte(),
                value
        );
    }
}
