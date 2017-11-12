package pl.grzeslowski.jsupla.protocoljava.api.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;

public interface RegisterDeviceResultParser
        extends ServerDeviceParser<RegisterDeviceResult, SuplaRegisterDeviceResult> {
}
