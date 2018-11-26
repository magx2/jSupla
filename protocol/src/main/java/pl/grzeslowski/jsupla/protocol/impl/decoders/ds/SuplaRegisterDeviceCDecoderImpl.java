package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceCDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@Deprecated
public final class SuplaRegisterDeviceCDecoderImpl implements SuplaRegisterDeviceCDecoder {
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaDeviceChannelBDecoder channelBDecoder;

    public SuplaRegisterDeviceCDecoderImpl(PrimitiveDecoder primitiveDecoder,
                                           SuplaDeviceChannelBDecoder channelBDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.channelBDecoder = requireNonNull(channelBDecoder);
    }

    @Override
    public SuplaRegisterDeviceC decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaRegisterDeviceC.SIZE);

        final int locationId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] locationPwd = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_LOCATION_PWD_MAXSIZE);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;

        final byte[] guid = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        final byte[] softVer = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final byte[] serverName = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);
        offset += SUPLA_SERVER_NAME_MAXSIZE;

        final short channelCount = primitiveDecoder.parseUnsignedByte(bytes, offset);
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
