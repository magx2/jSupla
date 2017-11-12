package pl.grzeslowski.jsupla.protocoljava.api.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;

public interface LocationPackSerializer extends ServerClientSerializer<LocationPack, SuplaLocationPack> {
}
