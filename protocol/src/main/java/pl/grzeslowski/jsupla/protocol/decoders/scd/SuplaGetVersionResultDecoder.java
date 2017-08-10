package pl.grzeslowski.jsupla.protocol.decoders.scd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.scd.SuplaGetVersionResult;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class SuplaGetVersionResultDecoder implements ServerClientDeviceDecoder<SuplaGetVersionResult> {
    @Override
    public SuplaGetVersionResult decode(byte[] bytes, int offset) {
        final short protoVersionMin = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short protoVersion = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);

        return new SuplaGetVersionResult(protoVersionMin, protoVersion, softVer);
    }
}
