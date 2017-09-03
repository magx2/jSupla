package pl.grzeslowski.jsupla.protocol.common.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelValueRandomizer implements Randomizer<SuplaChannelValue> {
    private final RandomBean randomBean;

    public SuplaChannelValueRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelValue getRandomValue() {
        return new SuplaChannelValue(
                                            randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE),
                                            randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
