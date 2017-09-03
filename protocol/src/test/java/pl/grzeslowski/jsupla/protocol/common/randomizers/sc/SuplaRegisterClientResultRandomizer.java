package pl.grzeslowski.jsupla.protocol.common.randomizers.sc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

public class SuplaRegisterClientResultRandomizer implements Randomizer<SuplaRegisterClientResult> {
    private final RandomBean randomBean;

    public SuplaRegisterClientResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaRegisterClientResult getRandomValue() {
        final short version = randomBean.nextUnsignedByte();
        return new SuplaRegisterClientResult(
                                                    randomBean.nextPositiveInt(),
                                                    randomBean.nextPositiveInt(),
                                                    randomBean.nextPositiveInt(),
                                                    randomBean.nextPositiveInt(),
                                                    randomBean.nextUnsignedByte(),
                                                    version,
                                                    randomBean.nextUnsignedByte(version)
        );
    }
}
