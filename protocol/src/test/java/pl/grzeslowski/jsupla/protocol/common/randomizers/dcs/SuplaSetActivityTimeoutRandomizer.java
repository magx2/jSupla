package pl.grzeslowski.jsupla.protocol.common.randomizers.dcs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaSetActivityTimeoutRandomizer implements Randomizer<SuplaSetActivityTimeout> {
    private final RandomSupla randomSupla;

    public SuplaSetActivityTimeoutRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaSetActivityTimeout getRandomValue() {
        return new SuplaSetActivityTimeout(randomSupla.nextUnsignedByte());
    }
}
