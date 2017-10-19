package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class LocationSerializerImpl implements LocationSerializer {
    private final StringSerializer stringSerializer;

    public LocationSerializerImpl(final StringSerializer stringSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaLocation serialize(@NotNull final Location entity) {
        return new SuplaLocation(
                                        (byte) entity.getEol(),
                                        entity.getId(),
                                        entity.getCaption().length(),
                                        stringSerializer.serialize(entity.getCaption())
        );
    }
}
