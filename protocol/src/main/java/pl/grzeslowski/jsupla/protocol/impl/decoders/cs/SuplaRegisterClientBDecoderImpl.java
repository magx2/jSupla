package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.cs.ClientServerDecoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaRegisterClientB;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

public final class SuplaRegisterClientBDecoderImpl implements ClientServerDecoder<SuplaRegisterClientB> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaRegisterClientBDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaRegisterClientB decode(byte[] bytes, int offset) {
        final int accessId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] accessIdPwd = Arrays.copyOfRange(bytes, offset, offset + SUPLA_ACCESSID_PWD_MAXSIZE);
        offset += SUPLA_ACCESSID_PWD_MAXSIZE;

        final byte[] guid = Arrays.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CLIENT_NAME_MAXSIZE);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final byte[] serverName = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SERVER_NAME_MAXSIZE);

        return new SuplaRegisterClientB(accessId, accessIdPwd, guid, name, softVer, serverName);
    }
}
