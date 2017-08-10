package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDevice;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

@Deprecated
public final class SuplaRegisterDeviceDecoder implements DeviceServerDecoder<SuplaRegisterDevice> {
    private final SuplaDeviceChannelDecoder channelDecoder;

    public SuplaRegisterDeviceDecoder(SuplaDeviceChannelDecoder channelDecoder) {
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public SuplaRegisterDevice decode(byte[] bytes, int offset) {
        final int locationId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] locationPwd = Arrays.copyOfRange(bytes, offset, offset + SUPLA_LOCATION_PWD_MAXSIZE);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;

        final byte[] guid = Arrays.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = Arrays.copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final short channelCount = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += INT_SIZE;

        final SuplaDeviceChannel[] channels = new SuplaDeviceChannel[channelCount];
        for (int i = 0; i < channelCount; i++) {
            channels[i] = channelDecoder.decode(bytes, offset);
            offset += channels[i].size();
        }

        return new SuplaRegisterDevice(locationId, locationPwd, guid, name, softVer, channelCount, channels);
    }
}
