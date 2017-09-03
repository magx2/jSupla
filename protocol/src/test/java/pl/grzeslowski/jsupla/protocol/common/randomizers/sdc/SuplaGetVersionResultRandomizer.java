package pl.grzeslowski.jsupla.protocol.common.randomizers.sdc;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaGetVersionResultRandomizer implements Randomizer<SuplaGetVersionResult> {
    private final RandomBean randomBean;

    public SuplaGetVersionResultRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaGetVersionResult getRandomValue() {
        final short protoVersion = randomBean.nextUnsignedByte();
        final short protoVersionMin = randomBean.nextUnsignedByte(protoVersion);
        return new SuplaGetVersionResult(
                                                protoVersionMin,
                                                protoVersion,
                                                randomBean.nextByteArray(SUPLA_SOFTVER_MAXSIZE)
        );
    }
}
