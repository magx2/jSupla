package pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;

import javax.validation.constraints.NotNull;

public class SetActivityTimeoutParserImpl implements SetActivityTimeoutParser {
    @Override
    public SetActivityTimeout parse(@NotNull final SuplaSetActivityTimeout proto) {
        return new SetActivityTimeout(proto.activityTimeout);
    }
}
