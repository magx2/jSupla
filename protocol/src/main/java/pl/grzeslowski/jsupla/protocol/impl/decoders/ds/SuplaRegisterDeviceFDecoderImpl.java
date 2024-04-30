package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import lombok.RequiredArgsConstructor;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelDDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceFDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelC;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelD;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceF;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@RequiredArgsConstructor
public final class SuplaRegisterDeviceFDecoderImpl implements SuplaRegisterDeviceFDecoder {
    public static final SuplaRegisterDeviceFDecoderImpl INSTANCE = new SuplaRegisterDeviceFDecoderImpl(
        SuplaDeviceChannelDDecoderImpl.INSTANCE);
    private final SuplaDeviceChannelDDecoder suplaDeviceChannelDDecoder;

    @Override
    public SuplaRegisterDeviceF decode(final byte[] bytes, int offset) {
        val email = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_EMAIL_MAXSIZE);
        offset += SUPLA_EMAIL_MAXSIZE;

        val authKey = PrimitiveDecoderImpl.INSTANCE
            .copyOfRange(bytes, offset, offset + SUPLA_AUTHKEY_SIZE);
        offset += SUPLA_AUTHKEY_SIZE;

        val guid = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        val name = PrimitiveDecoderImpl.INSTANCE
            .copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        val softVer = PrimitiveDecoderImpl.INSTANCE
            .copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        val serverName = PrimitiveDecoderImpl.INSTANCE
            .copyOfRange(bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);
        offset += SUPLA_SERVER_NAME_MAXSIZE;

        val flags = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        val manufacturerId = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val productId = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val channelCount = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val channels = new SuplaDeviceChannelD[channelCount];
        for (int i = 0; i < channelCount; i++) {
            if (bytes.length - offset < SuplaDeviceChannelC.SIZE) {
                throw new IllegalArgumentException(format(
                    "Can't parse SuplaDeviceChannelC from byte array of length %s with offset %s, " +
                        "because length is %s!", bytes.length, offset, SuplaDeviceChannelC.SIZE));
            }
            channels[i] = suplaDeviceChannelDDecoder.decode(bytes, offset);
            offset += SuplaDeviceChannelC.SIZE;
        }

        return new SuplaRegisterDeviceF(
            email,
            authKey,
            guid,
            name,
            softVer,
            serverName,
            flags,
            manufacturerId,
            productId,
            channelCount,
            channels);
    }
}
