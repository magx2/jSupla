package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class LocationPackSerializerImpl implements LocationPackSerializer {
    public static final LocationPackSerializerImpl INSTANCE = new LocationPackSerializerImpl(
            LocationSerializerImpl.INSTANCE);
    private final LocationSerializer locationSerializer;

    LocationPackSerializerImpl(final LocationSerializer locationSerializer) {
        this.locationSerializer = requireNonNull(locationSerializer);
    }

    @Override
    public SuplaLocationPack serialize(@NotNull final LocationPack entity) {
        return new SuplaLocationPack(
                                            entity.getLocations().size(),
                                            entity.getTotalLeft(),
                                            entity.getLocations()
                                                    .stream()
                                                    .map(locationSerializer::serialize)
                                                    .toArray(SuplaLocation[]::new)
        );
    }
}
