package pl.grzeslowski.jsupla.protocol.common.randomizers.dcs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaPingServerRandomizer implements Randomizer<SuplaPingServer> {
    private final RandomBean randomBean;

    public SuplaPingServerRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaPingServer getRandomValue() {
        return new SuplaPingServer(randomBean.nextObject(SuplaTimeval.class));
    }
}
