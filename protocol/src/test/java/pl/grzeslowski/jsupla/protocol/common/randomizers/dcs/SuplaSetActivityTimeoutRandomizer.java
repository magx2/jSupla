package pl.grzeslowski.jsupla.protocol.common.randomizers.dcs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaSetActivityTimeoutRandomizer implements Randomizer<SuplaSetActivityTimeout> {
    private final RandomBean randomBean;

    public SuplaSetActivityTimeoutRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaSetActivityTimeout getRandomValue() {
        return new SuplaSetActivityTimeout(randomBean.nextUnsignedByte());
    }
}
