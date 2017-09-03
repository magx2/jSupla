package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaVersionErrorRandomizer implements Randomizer<SuplaVersionError> {
    private final RandomBean randomBean;

    public SuplaVersionErrorRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaVersionError getRandomValue() {
        return new SuplaVersionError(
                                            randomBean.nextUnsignedByte(),
                                            randomBean.nextUnsignedByte()

        );
    }
}
