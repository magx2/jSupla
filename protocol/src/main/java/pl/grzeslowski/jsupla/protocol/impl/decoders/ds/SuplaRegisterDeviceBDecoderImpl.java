package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public final class SuplaRegisterDeviceBDecoderImpl implements SuplaRegisterDeviceBDecoder {
    public static final SuplaRegisterDeviceBDecoderImpl INSTANCE = new SuplaRegisterDeviceBDecoderImpl(
            PrimitiveDecoderImpl.INSTANCE, SuplaDeviceChannelBDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaDeviceChannelBDecoder channelDecoder;

    public SuplaRegisterDeviceBDecoderImpl(PrimitiveDecoder primitiveDecoder,
                                           SuplaDeviceChannelBDecoder channelDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public SuplaRegisterDeviceB decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaRegisterDeviceB.MIN_SIZE);

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

        final short channelCount = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[channelCount];
        for (int i = 0; i < channelCount; i++) {
            channels[i] = channelDecoder.decode(bytes, offset);
            offset += channels[i].size();
        }

        return new SuplaRegisterDeviceB(locationId, locationPwd, guid, name, softVer, channelCount, channels);
    }
}
