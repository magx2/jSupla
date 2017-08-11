package pl.grzeslowski.jsupla.protocol.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaGetVersionResult;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class SuplaGetVersionResultDecoder implements ServerClientDeviceDecoder<SuplaGetVersionResult> {
    @Override
    public SuplaGetVersionResult decode(byte[] bytes, int offset) {
        final short protoVersionMin = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short protoVersion = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);

        return new SuplaGetVersionResult(protoVersionMin, protoVersion, softVer);
    }
}
