package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelNewValueRandomizer implements Randomizer<SuplaChannelNewValue> {
    private final RandomSupla randomSupla;

    public SuplaChannelNewValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelNewValue getRandomValue() {
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaChannelNewValueA(randomSupla.nextByte(), value);
    }
}
