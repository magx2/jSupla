package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.RegisterClientResultBParser;

import javax.validation.constraints.NotNull;

public class RegisterClientResultBParserImpl implements RegisterClientResultBParser {
    @Override
    public RegisterClientResultB parse(@NotNull SuplaRegisterClientResultB proto) {
        return new RegisterClientResultB(
                proto.resultCode,
                proto.clientId,
                proto.locationCount,
                proto.channelCount,
                proto.activityTimeout,
                proto.version,
                proto.versionMin,
                proto.flags
        );
    }
}
