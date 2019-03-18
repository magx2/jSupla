package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaRegisterDeviceCRandomizer implements Randomizer<SuplaRegisterDeviceC> {
    private final RandomSupla randomSupla;

    public SuplaRegisterDeviceCRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterDeviceC getRandomValue() {
        final short channelCount = randomSupla.nextUnsignedByte((short) SUPLA_CHANNELMAXCOUNT);
        final SuplaDeviceChannelB[] channels = randomSupla.objects(SuplaDeviceChannelB.class, channelCount)
                                                   .toArray(SuplaDeviceChannelB[]::new);
        return new SuplaRegisterDeviceC(
            randomSupla.nextPositiveInt(),
            randomSupla.nextByteArrayFromString(SUPLA_LOCATION_PWD_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_GUID_SIZE),
            randomSupla.nextByteArrayFromString(SUPLA_DEVICE_NAME_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_SOFTVER_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_SERVER_NAME_MAXSIZE),
            channelCount,
            channels
        );
    }
}
