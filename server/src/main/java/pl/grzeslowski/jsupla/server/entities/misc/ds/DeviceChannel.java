package pl.grzeslowski.jsupla.server.entities.misc.ds;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.server.entities.Entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class DeviceChannel implements Entity {
    @Min(0)
    @Max(255)
    private final int number;
    @Min(0)
    private final int type;
    @NotNull
    @Size(max = SUPLA_CHANNELVALUE_SIZE)
    private final ChannelValue value;

    public DeviceChannel(int number, int type, ChannelValue value) {
        this.number = unsignedByteSize(number);
        this.type = min(type, 0);
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public ChannelValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DeviceChannel{" +
                       "number=" + number +
                       ", type=" + type +
                       ", value=" + value +
                       '}';
    }
}
