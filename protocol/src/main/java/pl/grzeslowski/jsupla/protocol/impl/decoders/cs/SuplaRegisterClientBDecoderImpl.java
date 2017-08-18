package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB.SIZE;

public final class SuplaRegisterClientBDecoderImpl implements SuplaRegisterClientBDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaRegisterClientBDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaRegisterClientB decode(byte[] bytes, int offset) {
        Preconditions.checkArrayLength(bytes, offset + SIZE);

        final int accessId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] accessIdPwd = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_ACCESSID_PWD_MAXSIZE);
        offset += SUPLA_ACCESSID_PWD_MAXSIZE;

        final byte[] guid = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CLIENT_NAME_MAXSIZE);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;

        final byte[] softVer = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final byte[] serverName = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);

        return new SuplaRegisterClientB(accessId, accessIdPwd, guid, name, softVer, serverName);
    }
}
