package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaLocationRandomizer implements Randomizer<SuplaLocation> {
    private final RandomBean randomBean;

    public SuplaLocationRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaLocation getRandomValue() {
        final long captionSize = randomBean.nextUnsignedInt();
        return new SuplaLocation(
                                        randomBean.nextByte(),
                                        randomBean.nextPositiveInt(),
                                        captionSize,
                                        randomBean.nextByteArray((int) captionSize)
        );
    }
}
