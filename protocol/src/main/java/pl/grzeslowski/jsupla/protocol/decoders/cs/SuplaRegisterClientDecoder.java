package pl.grzeslowski.jsupla.protocol.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaRegisterClient;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

@Deprecated
public class SuplaRegisterClientDecoder implements ClientServerDecoder<SuplaRegisterClient> {
    @Override
    public SuplaRegisterClient decode(byte[] bytes, int offset) {
        final int accessId = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] accessIdPwd = Arrays.copyOfRange(bytes, offset, offset + SUPLA_ACCESSID_PWD_MAXSIZE);
        offset += SUPLA_ACCESSID_PWD_MAXSIZE;

        final byte[] guid = Arrays.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CLIENT_NAME_MAXSIZE);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);

        return new SuplaRegisterClient(accessId, accessIdPwd, guid, name, softVer);
    }
}
