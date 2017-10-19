package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.SetActivityTimeoutResultParser;

import javax.validation.constraints.NotNull;

public class SetActivityTimeoutResultParserImpl implements SetActivityTimeoutResultParser {
    @Override
    public SetActivityTimeoutResult parse(@NotNull final SuplaSetActivityTimeoutResult proto) {
        return new SetActivityTimeoutResult(
                                                   proto.activityTimeout,
                                                   proto.min,
                                                   proto.max
        );
    }
}
