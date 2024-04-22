package pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;

public interface SetActivityTimeoutResultParser
    extends ServerDeviceClientParser<SetActivityTimeoutResult, SuplaSetActivityTimeoutResult> {
}
