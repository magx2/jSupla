package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;

import javax.validation.constraints.NotNull;

public class RegisterDeviceResultParserImpl implements RegisterDeviceResultParser {
    @Override
    public RegisterDeviceResult parse(@NotNull final SuplaRegisterDeviceResult proto) {
        return new RegisterDeviceResult(
                                               proto.resultCode,
                                               proto.activityTimeout,
                                               proto.version,
                                               proto.versionMin
        );
    }
}
