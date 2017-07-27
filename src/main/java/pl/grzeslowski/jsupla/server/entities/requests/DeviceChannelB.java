package pl.grzeslowski.jsupla.server.entities.requests;

import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.server.entities.Entity.Version.B;

public class DeviceChannelB extends DeviceChannel {
    @Min(0)
    private final int funcList;
    @Min(0)
    private final int defaultValue;

    public DeviceChannelB(int number, int type, byte[] value, int funcList, int defaultValue) {
        super(number, type, value);
        this.funcList = min(funcList, 0);
        this.defaultValue = min(defaultValue, 0);
    }

    public int getFuncList() {
        return funcList;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Version version() {
        return B;
    }

    @Override
    public String toString() {
        return "DeviceChannelB{" +
                "funcList=" + funcList +
                ", defaultValue=" + defaultValue +
                "} " + super.toString();
    }
}