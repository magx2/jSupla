package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.server.entities.Entity.Version.C;

public class DeviceRegisterEventC extends DeviceRegisterEventB {
    @NotNull
    @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE)
    private final String serverName;

    public DeviceRegisterEventC(int locationId, String locationPassword, String guid, String name, String softVersion,
                                List<? extends DeviceChannelB> channels, String serverName) {
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
        return "DeviceRegisterEventC{" +
                "serverName='" + serverName + '\'' +
                "} " + super.toString();
    }
}
