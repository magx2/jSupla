package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaDeviceChannelValueRandomizer implements Randomizer<SuplaDeviceChannelValue> {
    private final RandomSupla randomSupla;

    public SuplaDeviceChannelValueRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaDeviceChannelValue getRandomValue() {
        return new SuplaDeviceChannelValue(
                                                  randomSupla.nextUnsignedByte(),
                                                  randomSupla.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
