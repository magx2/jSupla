package pl.grzeslowski.jsupla.server.api.entities.requests.ds;

import pl.grzeslowski.jsupla.server.api.entities.Entity;
import pl.grzeslowski.jsupla.server.api.entities.misc.ds.DeviceChannelsB;

import static pl.grzeslowski.jsupla.server.api.entities.Entity.Version.B;

public class RegisterDeviceRequestB extends RegisterDeviceRequest {

    public RegisterDeviceRequestB(int locationId, String locationPassword, String guid, String name, String softVersion,
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
        return "RegisterDeviceRequestB{} " + super.toString();
    }
}
