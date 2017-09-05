package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class TimevalRandomizer implements Randomizer<SuplaTimeval> {
    private final RandomBean randomBean;

    public TimevalRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaTimeval getRandomValue() {
        return new SuplaTimeval(
                                  randomBean.nextPositiveInt(),
                                  randomBean.nextPositiveInt()
        );
    }
}
