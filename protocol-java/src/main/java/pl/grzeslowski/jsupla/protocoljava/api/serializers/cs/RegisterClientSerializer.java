package pl.grzeslowski.jsupla.protocoljava.api.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;

public interface RegisterClientSerializer extends ClientServerSerializer<RegisterClient, SuplaRegisterClient> {
}
