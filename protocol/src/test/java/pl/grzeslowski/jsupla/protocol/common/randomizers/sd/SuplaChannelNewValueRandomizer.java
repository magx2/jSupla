package pl.grzeslowski.jsupla.protocol.common.randomizers.sd;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelNewValueRandomizer implements Randomizer<SuplaChannelNewValue> {
    private final RandomBean randomBean;

    public SuplaChannelNewValueRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelNewValue getRandomValue() {
        return new SuplaChannelNewValue(
                                               randomBean.nextInt(),
                                               randomBean.nextUnsignedByte(),
                                               randomBean.nextUnsignedInt(),
                                               randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
