package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.RegisterClientResultParser;

import javax.validation.constraints.NotNull;

public class RegisterClientResultParserImpl implements RegisterClientResultParser {
    @Override
    public RegisterClientResult parse(@NotNull final SuplaRegisterClientResult proto) {
        return new RegisterClientResult(
                                               proto.resultCode,
                                               proto.clientId,
                                               proto.locationCount,
                                               proto.channelCount,
                                               proto.activityTimeout,
                                               proto.version,
                                               proto.versionMin
        );
    }
}
