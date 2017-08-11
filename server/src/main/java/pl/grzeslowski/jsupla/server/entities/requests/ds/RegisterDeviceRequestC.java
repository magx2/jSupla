package pl.grzeslowski.jsupla.server.entities.requests.ds;

import pl.grzeslowski.jsupla.server.entities.Entity;
import pl.grzeslowski.jsupla.server.entities.misc.ds.DeviceChannelsB;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.server.entities.Entity.Version.C;

public class RegisterDeviceRequestC extends RegisterDeviceRequestB {
    @NotNull
    @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE)
    private final String serverName;

    public RegisterDeviceRequestC(int locationId, String locationPassword, String guid, String name, String softVersion,
                                  DeviceChannelsB channels, String serverName) {
        super(locationId, locationPassword, guid, name, softVersion, channels);
        this.serverName = size(serverName, 1, SUPLA_SERVER_NAME_MAXSIZE);
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public Entity.Version version() {
        return C;
    }

    @Override
    public String toString() {
        return "RegisterDeviceRequestC{" +
                "serverName='" + serverName + '\'' +
                "} " + super.toString();
    }
}
