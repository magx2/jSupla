package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class TimevalRandomizer implements Randomizer<SuplaTimeval> {
    private final RandomSupla randomSupla;

    public TimevalRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaTimeval getRandomValue() {
        return new SuplaTimeval(
                                       randomSupla.nextPositiveInt(),
                                       randomSupla.nextPositiveInt()
        );
    }
}
