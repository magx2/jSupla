package pl.grzeslowski.jsupla.protocoljava.api.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;

@Deprecated
public interface RegisterClientParser extends ClientServerParser<RegisterClient, SuplaRegisterClient> {
}
