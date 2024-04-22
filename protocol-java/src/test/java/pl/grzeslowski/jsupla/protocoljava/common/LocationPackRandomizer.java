package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;

import java.util.List;
import java.util.stream.Collectors;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXSIZE;

class LocationPackRandomizer implements Randomizer<LocationPack> {
    private final EnhancedRandom random;

    LocationPackRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public LocationPack getRandomValue() {
        final List<Location> locations = random.objects(Location.class,
                random.nextInt(SUPLA_LOCATIONPACK_MAXSIZE - 1) + 1)
            .collect(Collectors.toList());
        return new LocationPack(random.nextInt(100), locations);
    }
}
