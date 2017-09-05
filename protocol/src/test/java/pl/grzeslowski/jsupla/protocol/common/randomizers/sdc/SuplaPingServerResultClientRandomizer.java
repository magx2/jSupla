package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaPingServerResultClientRandomizer implements Randomizer<SuplaPingServerResultClient> {
    private final RandomBean randomBean;

    public SuplaPingServerResultClientRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaPingServerResultClient getRandomValue() {
        return new SuplaPingServerResultClient(
                                                      randomBean.nextObject(SuplaTimeval.class)
        );
    }
}
