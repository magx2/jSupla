package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class TimevalRandomizer implements Randomizer<Timeval> {
    private final RandomBean randomBean;

    public TimevalRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public Timeval getRandomValue() {
        return new Timeval(
                                  randomBean.nextPositiveInt(),
                                  randomBean.nextPositiveInt()
        );
    }
}
