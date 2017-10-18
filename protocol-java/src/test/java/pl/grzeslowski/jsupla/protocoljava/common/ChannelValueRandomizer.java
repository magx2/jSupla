package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.DecimalValue;

public class ChannelValueRandomizer implements Randomizer<ChannelValue> {
    private final EnhancedRandom random;

    public ChannelValueRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public ChannelValue getRandomValue() {
        return new DecimalValue(random.nextDouble());
    }
}
