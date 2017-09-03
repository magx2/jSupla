package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static java.util.stream.Collectors.toList;

public class SuplaLocationPackRandomizer implements Randomizer<SuplaLocationPack> {
    private final RandomBean randomBean;

    public SuplaLocationPackRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaLocationPack getRandomValue() {
        final int count = randomBean.nextInt();
        final SuplaLocation[] locations = randomBean.objects(SuplaLocation.class, count)
                                                  .collect(toList())
                                                  .toArray(new SuplaLocation[0]);
        return new SuplaLocationPack(
                                            count,
                                            randomBean.nextInt(),
                                            locations
        );
    }
}
