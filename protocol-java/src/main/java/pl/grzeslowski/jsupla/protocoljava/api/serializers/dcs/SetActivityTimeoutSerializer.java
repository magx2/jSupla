package pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;

public interface SetActivityTimeoutSerializer
        extends DeviceClientServerSerializer<SetActivityTimeout, SuplaSetActivityTimeout> {
}
