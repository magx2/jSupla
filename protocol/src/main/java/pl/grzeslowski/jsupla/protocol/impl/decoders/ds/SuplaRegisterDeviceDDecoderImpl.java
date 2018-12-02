package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceDDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class SuplaRegisterDeviceDDecoderImpl implements SuplaRegisterDeviceDDecoder {
    public static final SuplaRegisterDeviceDDecoderImpl INSTANCE = new SuplaRegisterDeviceDDecoderImpl(
            SuplaDeviceChannelBDecoderImpl.INSTANCE);
    private final SuplaDeviceChannelBDecoder suplaDeviceChannelBDecoder;

    public SuplaRegisterDeviceDDecoderImpl(final SuplaDeviceChannelBDecoder suplaDeviceChannelBDecoder) {
        this.suplaDeviceChannelBDecoder = requireNonNull(suplaDeviceChannelBDecoder);
    }

    @Override
    public SuplaRegisterDeviceD decode(final byte[] bytes, int offset) {
        final byte[] email = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_EMAIL_MAXSIZE);
        offset += SUPLA_EMAIL_MAXSIZE;

        final byte[] authKey = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_AUTHKEY_SIZE);
        offset += SUPLA_AUTHKEY_SIZE;

        final byte[] guid = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        final byte[] softVer = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final byte[] serverName = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);
        offset += SUPLA_SERVER_NAME_MAXSIZE;

        final short channelCount = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[channelCount];
        for (int i = 0; i < channelCount; i++) {
            if (bytes.length - offset < SuplaDeviceChannelB.SIZE) {
                throw new IllegalArgumentException(format(
                        "Can't parse SuplaDeviceChannelB from byte array of length %s with offset %s, " +
                                "because length is %s!", bytes.length, offset, SuplaDeviceChannelB.SIZE));
            }
            channels[i] = suplaDeviceChannelBDecoder.decode(bytes, offset);
            offset += SuplaDeviceChannelB.SIZE;
        }

        return new SuplaRegisterDeviceD(
                email,
                authKey,
                guid,
                name,
                softVer,
                serverName,
                channelCount,
                channels
        );
    }
}
