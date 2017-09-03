package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaChannelValueRandomizer implements Randomizer<SuplaChannelValue> {
    private final RandomBean randomBean;

    public SuplaChannelValueRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelValue getRandomValue() {
        return new SuplaChannelValue(
                                            randomBean.nextByte(),
                                            randomBean.nextInt(),
                                            randomBean.nextByte(),
                                            randomBean.nextObject(
                                                    pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class)
        );
    }
}
