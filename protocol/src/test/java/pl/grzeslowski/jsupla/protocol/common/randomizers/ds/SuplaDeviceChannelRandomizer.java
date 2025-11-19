package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import io.github.benas.randombeans.api.Randomizer;
import java.util.concurrent.atomic.AtomicInteger;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelA;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaDeviceChannelRandomizer implements Randomizer<SuplaDeviceChannel> {
    private static final AtomicInteger NUMBER = new AtomicInteger();
    private final RandomSupla randomSupla;

    public SuplaDeviceChannelRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaDeviceChannel getRandomValue() {
        byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        value[0] = randomSupla.nextBoolByte();
        return new SuplaDeviceChannelA(
                getNumber(),
                2900, // ChannelType.SUPLA_CHANNELTYPE_RELAY(2900)
                value);
    }

    private short getNumber() {
        short number = (short) NUMBER.getAndIncrement();
        if (number > 255) {
            NUMBER.set(0);
            return getNumber();
        }
        return number;
    }
}
