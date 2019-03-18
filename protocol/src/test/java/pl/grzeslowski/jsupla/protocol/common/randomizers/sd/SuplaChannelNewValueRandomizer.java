package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelNewValueRandomizer implements Randomizer<SuplaChannelNewValue> {
    private final RandomSupla randomSupla;

    public SuplaChannelNewValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelNewValue getRandomValue() {
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaChannelNewValue(
                                               randomSupla.nextPositiveInt(),
                                               randomSupla.nextUnsignedByte(),
                                               randomSupla.nextUnsignedInt(),
                value
        );
    }
}
