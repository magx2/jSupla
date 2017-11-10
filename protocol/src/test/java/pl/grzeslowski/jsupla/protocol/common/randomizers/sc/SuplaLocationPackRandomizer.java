package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaLocationPackRandomizer implements Randomizer<SuplaLocationPack> {
    private final RandomSupla randomSupla;

    public SuplaLocationPackRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaLocationPack getRandomValue() {
        final int count = randomSupla.nextPositiveInt(20);
        final SuplaLocation[] locations = randomSupla.objects(SuplaLocation.class, count).toArray(SuplaLocation[]::new);
        return new SuplaLocationPack(
                                            count,
                                            randomSupla.nextPositiveInt(),
                                            locations
        );
    }
}
