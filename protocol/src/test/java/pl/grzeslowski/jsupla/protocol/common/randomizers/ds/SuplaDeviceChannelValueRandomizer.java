package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValueA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaDeviceChannelValueRandomizer implements Randomizer<SuplaDeviceChannelValue> {
    private final RandomSupla randomSupla;

    public SuplaDeviceChannelValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaDeviceChannelValue getRandomValue() {
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaDeviceChannelValueA(randomSupla.nextUnsignedByte(), value);
    }
}
