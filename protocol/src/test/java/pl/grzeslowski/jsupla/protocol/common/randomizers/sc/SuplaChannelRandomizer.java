package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaChannelRandomizer implements Randomizer<SuplaChannel> {
    private final RandomBean randomBean;

    public SuplaChannelRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannel getRandomValue() {
        final long captionSize = randomBean.nextUnsignedInt();
        return new SuplaChannel(
                                       randomBean.nextByte(),
                                       randomBean.nextPositiveInt(),
                                       randomBean.nextPositiveInt(),
                                       randomBean.nextPositiveInt(),
                                       randomBean.nextByte(),
                                       randomBean.nextObject(
                                               pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class),
                                       captionSize,
                                       randomBean.nextByteArray((int) captionSize)
        );
    }
}
