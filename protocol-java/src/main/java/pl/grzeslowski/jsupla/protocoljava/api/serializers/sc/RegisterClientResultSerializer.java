package pl.grzeslowski.jsupla.protocoljava.api.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;

public interface RegisterClientResultSerializer
        extends ServerClientSerializer<RegisterClientResult, SuplaRegisterClientResult> {
}
