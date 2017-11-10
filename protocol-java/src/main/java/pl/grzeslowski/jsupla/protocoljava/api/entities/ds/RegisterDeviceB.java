package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.B;

@Deprecated
public class RegisterDeviceB extends RegisterDevice {
    public RegisterDeviceB(final int locationId,
                           final @NotNull @Size(min = 1, max = SUPLA_LOCATION_PWD_MAXSIZE) char[] locationPassword,
                           final @NotNull @Size(min = 1, max = SUPLA_GUID_SIZE) String guid,
                           final @NotNull @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE) String name,
                           final @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer,
                           final @NotNull @Size(max = UNSIGNED_BYTE_MAX) int channelCount,
                           final @NotNull DeviceChannelsB channels) {
        super(locationId, locationPassword, guid, name, softVer, channelCount, channels);
    }

    @Override
    public Version version() {
        return B;
    }

    @Override
    public boolean equals(final Object o) {
        return canEqual(o) && super.equals(o);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RegisterDeviceB;
    }

    @Override
    public DeviceChannelsB getChannels() {
        return (DeviceChannelsB) super.getChannels();
    }

    @Override
    public String toString() {
        return "RegisterDeviceB{} " + super.toString();
    }
}
