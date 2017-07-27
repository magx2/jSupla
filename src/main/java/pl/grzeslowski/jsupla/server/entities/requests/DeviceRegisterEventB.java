package pl.grzeslowski.jsupla.server.entities.requests;

import pl.grzeslowski.jsupla.server.entities.Entity;

import java.util.List;

import static pl.grzeslowski.jsupla.server.entities.Entity.Version.B;

public class DeviceRegisterEventB extends DeviceRegisterEvent {

    public DeviceRegisterEventB(int locationId, String locationPassword, String guid, String name, String softVersion,
                                List<? extends DeviceChannelB> channels) {
        super(locationId, locationPassword, guid, name, softVersion, channels);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<? extends DeviceChannelB> getChannels() {
        return (List<? extends DeviceChannelB>) super.getChannels();
    }

    @Override
    public Entity.Version version() {
        return B;
    }

    @Override
    public String toString() {
        return "DeviceRegisterEventB{} " + super.toString();
    }
}
