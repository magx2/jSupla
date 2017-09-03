package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaDeviceChannelRandomizer implements Randomizer<SuplaDeviceChannel> {
    private final RandomBean randomBean;

    public SuplaDeviceChannelRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaDeviceChannel getRandomValue() {
        return new SuplaDeviceChannel(
                                             randomBean.nextUnsignedByte(),
                                             randomBean.nextInt(),
                                             randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
