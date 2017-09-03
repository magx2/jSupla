package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaSetActivityTimeoutResultRandomizer implements Randomizer<SuplaSetActivityTimeoutResult> {
    private final RandomBean randomBean;

    public SuplaSetActivityTimeoutResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaSetActivityTimeoutResult getRandomValue() {
        final short max = randomBean.nextUnsignedByte();
        final short min = randomBean.nextUnsignedByte(max);
        return new SuplaSetActivityTimeoutResult(
                                                        randomBean.nextUnsignedByte(),
                                                        min,
                                                        max
        );
    }
}
