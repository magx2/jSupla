package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;

public class NewValueRandomizer implements Randomizer<NewValue> {
    private final EnhancedRandom random;

    public NewValueRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public NewValue getRandomValue() {
        return new NewValue(
            random.nextInt(100),
            random.nextInt(100),
            random.nextBoolean() ? OnOff.ON : OnOff.OFF
        );
    }
}
