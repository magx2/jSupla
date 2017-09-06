package pl.grzeslowski.jsupla.protocoljava.api.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;

@Deprecated
public interface RegisterDeviceParser extends DeviceServerEntityParser<RegisterDevice, SuplaRegisterDevice> {
}
