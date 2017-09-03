package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.common.RandomBean;

import static java.util.stream.Collectors.toList;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterDeviceRandomizer implements Randomizer<SuplaRegisterDevice> {
    private final RandomBean randomBean;

    public SuplaRegisterDeviceRandomizer(final RandomBean randomBean) {
        this.randomBean = randomBean;
    }

    @Override
    public SuplaRegisterDevice getRandomValue() {
        final short channelCount = randomBean.nextUnsignedByte((short) SUPLA_CHANNELMAXCOUNT);
        final SuplaDeviceChannel[] channels = randomBean.objects(SuplaDeviceChannel.class, channelCount)
                                                      .collect(toList())
                                                      .toArray(new SuplaDeviceChannel[0]);
        return new SuplaRegisterDevice(
                                              randomBean.nextPositiveInt(),
                                              randomBean.nextByteArray(SUPLA_LOCATION_PWD_MAXSIZE),
                                              randomBean.nextByteArray(SUPLA_GUID_SIZE),
                                              randomBean.nextByteArray(SUPLA_DEVICE_NAME_MAXSIZE),
                                              randomBean.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
                                              channelCount,
                                              channels
        );
    }
}
