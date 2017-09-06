package pl.grzeslowski.jsupla.protocoljava.api.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;

@Deprecated
public interface RegisterDeviceSerializer extends DeviceServerEntitySerializer<RegisterDevice, SuplaRegisterDevice> {
}
