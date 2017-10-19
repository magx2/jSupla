package pl.grzeslowski.jsupla.protocol.common.randomizers.dcs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaPingServerRandomizer implements Randomizer<SuplaPingServer> {
    private final RandomSupla randomSupla;

    public SuplaPingServerRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaPingServer getRandomValue() {
        return new SuplaPingServer(randomSupla.nextObject(SuplaTimeval.class));
    }
}
