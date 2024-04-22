package pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;

public interface SetActivityTimeoutParser
    extends DeviceClientServerParser<SetActivityTimeout, SuplaSetActivityTimeout> {
}
