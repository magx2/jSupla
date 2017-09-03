package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelNewValueRandomizer implements Randomizer<SuplaChannelNewValue> {
    private final RandomBean randomBean;

    public SuplaChannelNewValueRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelNewValue getRandomValue() {
        return new SuplaChannelNewValue(randomBean.nextByte(), randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE));
    }
}
