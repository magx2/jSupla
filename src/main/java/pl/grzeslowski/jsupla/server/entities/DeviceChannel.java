package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class DeviceChannel implements Entity {
    @Min(0)
    private final int number;
    @Min(0)
    private final int type;
    @Size(max = SUPLA_CHANNELVALUE_SIZE)
    private final byte[] value;

    public DeviceChannel(int number, int type, byte[] value) {
        this.number = min(number, 0);
        this.type = min(type, 0);
        this.value = sizeMax(value, SUPLA_CHANNELVALUE_SIZE);
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public byte[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DeviceChannel{" +
                "number=" + number +
                ", type=" + type +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
