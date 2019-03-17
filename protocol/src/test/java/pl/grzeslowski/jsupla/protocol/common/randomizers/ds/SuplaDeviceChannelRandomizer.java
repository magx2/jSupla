package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaDeviceChannelRandomizer implements Randomizer<SuplaDeviceChannel> {
    private final RandomSupla randomSupla;

    public SuplaDeviceChannelRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaDeviceChannel getRandomValue() {
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaDeviceChannel(
                                             randomSupla.nextUnsignedByte(),
                2900, // ChannelType.SUPLA_CHANNELTYPE_RELAY(2900)
                value
        );
    }
}
