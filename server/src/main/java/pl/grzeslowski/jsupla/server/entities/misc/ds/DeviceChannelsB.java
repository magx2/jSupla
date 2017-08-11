package pl.grzeslowski.jsupla.server.entities.misc.ds;

import java.util.List;

import static pl.grzeslowski.jsupla.server.entities.Entity.Version.B;

public class DeviceChannelsB extends DeviceChannels {
    public DeviceChannelsB(List<? extends DeviceChannelB> channels) {
        super(channels);
    }

    @Override
    public DeviceChannelB get(int i) {
        return (DeviceChannelB) super.get(i);
    }

    @Override
    public Version version() {
        return B;
    }

    @Override
    public String toString() {
        return "DeviceChannelsB{} " + super.toString();
    }
}
