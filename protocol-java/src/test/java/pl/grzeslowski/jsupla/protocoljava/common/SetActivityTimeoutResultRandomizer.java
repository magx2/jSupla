package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

class SetActivityTimeoutResultRandomizer implements Randomizer<SetActivityTimeoutResult> {
    private final EnhancedRandom random;

    SetActivityTimeoutResultRandomizer(final EnhancedRandom random) {
        this.random = random;
    }

    @Override
    public SetActivityTimeoutResult getRandomValue() {
        final int max = random.nextInt(UNSIGNED_BYTE_MAX - 2) + 1;
        final int min = random.nextInt(max);
        return new SetActivityTimeoutResult(random.nextInt(UNSIGNED_BYTE_MAX), min, max);
    }
}
