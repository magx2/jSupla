package pl.grzeslowski.jsupla.server.entities.requests;

import pl.grzeslowski.jsupla.server.entities.Entity;
import pl.grzeslowski.jsupla.server.entities.misc.DeviceChannelsB;

import static pl.grzeslowski.jsupla.server.entities.Entity.Version.B;

public class DeviceRegisterRequestB extends DeviceRegisterRequest {

    public DeviceRegisterRequestB(int locationId, String locationPassword, String guid, String name, String softVersion,
                                  DeviceChannelsB channels) {
        super(locationId, locationPassword, guid, name, softVersion, channels);
    }

    @Override
    public DeviceChannelsB getChannels() {
        return (DeviceChannelsB) super.getChannels();
    }

    @Override
    public Entity.Version version() {
        return B;
    }

    @Override
    public String toString() {
        return "DeviceRegisterRequestB{} " + super.toString();
    }
}
