package pl.grzeslowski.jsupla.protocol.common.randomizers.ds;

import io.github.benas.randombeans.api.Randomizer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class SuplaRegisterDeviceRandomizer implements Randomizer<SuplaRegisterDevice> {
    private final RandomSupla randomSupla;

    public SuplaRegisterDeviceRandomizer(final RandomSupla randomSupla) {
        this.randomSupla = randomSupla;
    }

    @Override
    public SuplaRegisterDevice getRandomValue() {
        final short channelCount = randomSupla.nextUnsignedByte((short) SUPLA_CHANNELMAXCOUNT);
        final SuplaDeviceChannel[] channels = randomSupla.objects(SuplaDeviceChannel.class, channelCount)
                .toArray(SuplaDeviceChannel[]::new);
        return new SuplaRegisterDevice(
                                              randomSupla.nextPositiveInt(),
                                              randomSupla.nextByteArray(SUPLA_LOCATION_PWD_MAXSIZE),
                                              randomSupla.nextByteArray(SUPLA_GUID_SIZE),
                                              randomSupla.nextByteArray(SUPLA_DEVICE_NAME_MAXSIZE),
                                              randomSupla.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
                                              channelCount,
                                              channels
        );
    }
}
