package pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;

public interface GetVersionResultSerializer
        extends ServerDeviceClientSerializer<GetVersionResult, SuplaGetVersionResult> {
}
