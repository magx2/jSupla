package pl.grzeslowski.jsupla.protocoljava.api.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;

public interface RegisterClientResultParser
        extends ServerClientEntityParser<RegisterClientResult, SuplaRegisterClientResult> {
}
