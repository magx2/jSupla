package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaDeviceChannelValueRandomizer implements Randomizer<SuplaDeviceChannelValue> {
    private final RandomBean randomBean;

    public SuplaDeviceChannelValueRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaDeviceChannelValue getRandomValue() {
        return new SuplaDeviceChannelValue(
                                                  randomBean.nextUnsignedByte(),
                                                  randomBean.nextByteArray(SUPLA_CHANNELVALUE_SIZE)
        );
    }
}
