package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaDeviceChannelBRandomizer implements Randomizer<SuplaDeviceChannelB> {
    private final RandomSupla randomSupla;

    public SuplaDeviceChannelBRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaDeviceChannelB getRandomValue() {
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaDeviceChannelB(
                                              randomSupla.nextUnsignedByte(),
                2900, // ChannelType.SUPLA_CHANNELTYPE_RELAY(2900)
                                              randomSupla.nextPositiveInt(),
                                              randomSupla.nextPositiveInt(),
                value
        );
    }
}
