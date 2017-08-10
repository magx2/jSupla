package pl.grzeslowski.jsupla.protocol.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaRegisterClientB;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

public class SuplaRegisterClientBDecoder implements ClientServerDecoder<SuplaRegisterClientB> {
    @Override
    public SuplaRegisterClientB decode(byte[] bytes, int offset) {
        final int accessId = PrimitiveDecoder.parseInt(bytes, offset);
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