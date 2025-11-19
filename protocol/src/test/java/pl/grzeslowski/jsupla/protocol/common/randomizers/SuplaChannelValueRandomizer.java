package pl.grzeslowski.jsupla.protocol.common.randomizers;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValueA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaChannelValueRandomizer implements Randomizer<SuplaChannelValue> {
    private final RandomSupla randomSupla;

    public SuplaChannelValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaChannelValue getRandomValue() {
        return new SuplaChannelValueA(
                randomSupla.nextByteArray(SUPLA_CHANNELVALUE_SIZE),
                randomSupla.nextByteArray(SUPLA_CHANNELVALUE_SIZE));
    }
}
