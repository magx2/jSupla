package pl.grzeslowski.jsupla.protocoljava.api.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;

public interface LocationSerializer extends ServerClientSerializer<Location, SuplaLocation> {
}
