package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaDeviceChannelBRandomizer implements Randomizer<SuplaDeviceChannelB> {
    private final RandomBean randomBean;

    public SuplaDeviceChannelBRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaDeviceChannelB getRandomValue() {
        return new SuplaDeviceChannelB(
                                              randomBean.nextUnsignedByte(),
                                              randomBean.nextPositiveInt(),
                                              randomBean.nextPositiveInt(),
                                              randomBean.nextPositiveInt(),
                                              randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
