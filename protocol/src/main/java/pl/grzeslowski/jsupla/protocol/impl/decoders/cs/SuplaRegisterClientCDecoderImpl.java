package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientCDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

/**
 * @since ver. 7
 */
public final class SuplaRegisterClientCDecoderImpl implements SuplaRegisterClientCDecoder {
    public static final SuplaRegisterClientCDecoderImpl INSTANCE = new SuplaRegisterClientCDecoderImpl();
    
    @SuppressWarnings("UnusedAssignment")
    @Override
    public SuplaRegisterClientC decode(final byte[] bytes, int offset) {

        final byte[] email = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
                bytes, offset, offset + SUPLA_EMAIL_MAXSIZE);
        offset += SUPLA_EMAIL_MAXSIZE;

        final byte[] authKey = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
                bytes, offset, offset + SUPLA_AUTHKEY_SIZE);
        offset += SUPLA_AUTHKEY_SIZE;

        final byte[] guid = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
                bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
                bytes, offset, offset + SUPLA_CLIENT_NAME_MAXSIZE);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;

        final byte[] softVer = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
                bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final byte[] serverName = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
                bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);
        offset += SUPLA_SERVER_NAME_MAXSIZE;

        return new SuplaRegisterClientC(
                email,
                authKey,
                guid,
                name,
                softVer,
                serverName
        );
    }
}
