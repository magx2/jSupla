package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class DeviceChannel implements Entity {
    @Min(0)
    @Max(255)
    private final int number;
    @Min(0)
    private final int type;
    @NotNull
    @Size(max = SUPLA_CHANNELVALUE_SIZE)
    private final byte[] value; // TODO should be some object that can be mapped from `type`

    public DeviceChannel(int number, int type, byte[] value) {
        this.number = size(number, 0, 255);
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
