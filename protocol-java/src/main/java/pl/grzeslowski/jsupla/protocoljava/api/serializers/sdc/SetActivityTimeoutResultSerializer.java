package pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;

public interface SetActivityTimeoutResultSerializer
        extends ServerDeviceClientEntitySerializer<SetActivityTimeoutResult, SuplaSetActivityTimeoutResult> {
}
