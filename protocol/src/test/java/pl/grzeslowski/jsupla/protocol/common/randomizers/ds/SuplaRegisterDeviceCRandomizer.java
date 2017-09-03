package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static java.util.stream.Collectors.toList;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterDeviceCRandomizer implements Randomizer<SuplaRegisterDeviceC> {
    private final RandomBean randomBean;

    public SuplaRegisterDeviceCRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaRegisterDeviceC getRandomValue() {
        final short channelCount = randomBean.nextUnsignedByte((short) SUPLA_CHANNELMAXCOUNT);
        final SuplaDeviceChannelB[] channels = randomBean.objects(SuplaDeviceChannelB.class, channelCount)
                                                       .collect(toList())
                                                       .toArray(new SuplaDeviceChannelB[0]);
        return new SuplaRegisterDeviceC(
                                               randomBean.nextPositiveInt(),
                                               randomBean.nextByteArray(SUPLA_LOCATION_PWD_MAXSIZE),
                                               randomBean.nextByteArray(SUPLA_GUID_SIZE),
                                               randomBean.nextByteArray(SUPLA_DEVICE_NAME_MAXSIZE),
                                               randomBean.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
                                               randomBean.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE),
                                               channelCount,
                                               channels
        );
    }
}
