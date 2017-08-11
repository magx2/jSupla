package pl.grzeslowski.jsupla.server.entities.misc;

import java.util.List;

public class DeviceChannelsB extends DeviceChannels {
    public DeviceChannelsB(List<? extends DeviceChannelB> channels) {
        super(channels);
    }

    @Override
    public DeviceChannelB get(int i) {
        return (DeviceChannelB) super.get(i);
    }

    @Override
    public String toString() {
        return "DeviceChannelsB{} " + super.toString();
    }
}
