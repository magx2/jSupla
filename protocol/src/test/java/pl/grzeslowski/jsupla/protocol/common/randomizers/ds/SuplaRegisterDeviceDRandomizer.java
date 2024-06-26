package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class SuplaRegisterDeviceDRandomizer implements Randomizer<SuplaRegisterDeviceD> {
    private final RandomSupla randomSupla;

    public SuplaRegisterDeviceDRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterDeviceD getRandomValue() {
        final short channelCount = (short) (randomSupla.nextUnsignedByte((short) (SUPLA_CHANNELMAXCOUNT - 1)) + 1);
        final SuplaDeviceChannelB[] channels = randomSupla.objects(SuplaDeviceChannelB.class, channelCount)
            .toArray(SuplaDeviceChannelB[]::new);
        return new SuplaRegisterDeviceD(
            randomSupla.nextByteArrayFromString(SUPLA_EMAIL_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_AUTHKEY_SIZE),
            randomSupla.nextByteArrayFromString(SUPLA_GUID_SIZE),
            randomSupla.nextByteArrayFromString(SUPLA_DEVICE_NAME_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_SOFTVER_MAXSIZE),
            randomSupla.nextByteArrayFromString(SUPLA_SERVER_NAME_MAXSIZE),
            channelCount,
            channels
        );
    }
}
