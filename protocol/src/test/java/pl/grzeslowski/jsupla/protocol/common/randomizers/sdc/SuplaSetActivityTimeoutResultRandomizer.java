package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaSetActivityTimeoutResultRandomizer implements Randomizer<SuplaSetActivityTimeoutResult> {
    private final RandomSupla randomSupla;

    public SuplaSetActivityTimeoutResultRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaSetActivityTimeoutResult getRandomValue() {
        final short max = (short) (randomSupla.nextUnsignedByte() + 1);
        final short min = randomSupla.nextUnsignedByte(max);
        return new SuplaSetActivityTimeoutResult(
            randomSupla.nextUnsignedByte(),
            min,
            max
        );
    }
}
