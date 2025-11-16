package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

public class SuplaRegisterDeviceBRandomizer implements Randomizer<SuplaRegisterDeviceB> {
    private final RandomSupla randomSupla;

    public SuplaRegisterDeviceBRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterDeviceB getRandomValue() {
        final short channelCount = randomSupla.nextUnsignedByte((short) SUPLA_CHANNELMAXCOUNT);
        final SuplaDeviceChannelB[] channels =
                randomSupla
                        .objects(SuplaDeviceChannelB.class, channelCount)
                        .toArray(SuplaDeviceChannelB[]::new);
        return new SuplaRegisterDeviceB(
                randomSupla.nextPositiveInt(),
                randomSupla.nextByteArrayFromString(SUPLA_LOCATION_PWD_MAXSIZE),
                randomSupla.nextByteArrayFromString(SUPLA_GUID_SIZE),
                randomSupla.nextByteArrayFromString(SUPLA_DEVICE_NAME_MAXSIZE),
                randomSupla.nextByteArrayFromString(SUPLA_SOFTVER_MAXSIZE),
                channelCount,
                channels);
    }
}
