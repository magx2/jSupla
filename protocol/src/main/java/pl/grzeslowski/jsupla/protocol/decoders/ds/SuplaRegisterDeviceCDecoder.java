package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceC;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

public class SuplaRegisterDeviceCDecoder implements DeviceServerDecoder<SuplaRegisterDeviceC> {
    private final SuplaDeviceChannelBDecoder channelBDecoder;

    public SuplaRegisterDeviceCDecoder(SuplaDeviceChannelBDecoder channelBDecoder) {
        this.channelBDecoder = requireNonNull(channelBDecoder);
    }

    @Override
    public SuplaRegisterDeviceC decode(byte[] bytes, int offset) {
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

        final byte[] serverName = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);
        offset += SUPLA_SERVER_NAME_MAXSIZE;

        final short channelCount = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[channelCount];
        for (int i = 0; i < channelCount; i++) {
            channels[i] = channelBDecoder.decode(bytes, offset);
            offset += channels[i].size();
        }

        return new SuplaRegisterDeviceC(
                locationId,
                locationPwd,
                guid,
                name,
                softVer,
                serverName,
                channelCount,
                channels);
    }
}
