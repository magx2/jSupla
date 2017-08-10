package pl.grzeslowski.jsupla.protocol.decoders.scd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.scd.SuplaVersionError;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public class SuplaVersionErrorDecoder implements ServerClientDeviceDecoder<SuplaVersionError> {
    @Override
    public SuplaVersionError decode(byte[] bytes, int offset) {
        final short serverVersionMin = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short serverVersion = PrimitiveDecoder.parseUnsignedByte(bytes, offset);

        return new SuplaVersionError(serverVersionMin, serverVersion);
    }
}
