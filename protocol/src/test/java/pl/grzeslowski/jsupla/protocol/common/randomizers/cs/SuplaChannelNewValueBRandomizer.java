package pl.grzeslowski.jsupla.protocol.common.randomizers.cs;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelNewValueBRandomizer implements Randomizer<SuplaChannelNewValueB> {
    private final RandomBean randomBean;

    public SuplaChannelNewValueBRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaChannelNewValueB getRandomValue() {
        return new SuplaChannelNewValueB(
                                                randomBean.nextPositiveInt(),
                                                randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
