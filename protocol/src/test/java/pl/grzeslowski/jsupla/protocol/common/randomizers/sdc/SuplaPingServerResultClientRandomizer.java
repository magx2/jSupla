package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaPingServerResultClientRandomizer implements Randomizer<SuplaPingServerResultClient> {
    private final RandomSupla randomSupla;

    public SuplaPingServerResultClientRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaPingServerResultClient getRandomValue() {
        return new SuplaPingServerResultClient(
            randomSupla.nextObject(SuplaTimeval.class)
        );
    }
}
