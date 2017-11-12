package pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;

public interface GetVersionResultParser
        extends ServerDeviceClientParser<GetVersionResult, SuplaGetVersionResult> {
}
