package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static java.util.stream.Collectors.toList;

public class SuplaLocationPackRandomizer implements Randomizer<SuplaLocationPack> {
    private final RandomSupla randomSupla;

    public SuplaLocationPackRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaLocationPack getRandomValue() {
        final int count = randomSupla.nextPositiveInt(20);
        final SuplaLocation[] locations = randomSupla.objects(SuplaLocation.class, count)
                                                  .collect(toList())
                                                  .toArray(new SuplaLocation[0]);
        return new SuplaLocationPack(
                                            count,
                                            randomSupla.nextPositiveInt(),
                                            locations
        );
    }
}
