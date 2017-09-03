package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaChannelNewValueResultRandomizer implements Randomizer<SuplaChannelNewValueResult> {
    private final RandomBean randomBean;

    public SuplaChannelNewValueResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelNewValueResult getRandomValue() {
        return new SuplaChannelNewValueResult(
                                                     randomBean.nextUnsignedByte(),
                                                     randomBean.nextInt(),
                                                     randomBean.nextByte());
    }
}
