package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaRegisterClientResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public class SuplaRegisterClientResultDecoder implements ServerClientDecoder<SuplaRegisterClientResult> {
    @Override
    public SuplaRegisterClientResult decode(byte[] bytes, int offset) {
        final int resultCode = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int clientId = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationCount = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelCount = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final short activityTimeout = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short version = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short versionMin = PrimitiveParser.parseUnsignedByte(bytes, offset);

        return new SuplaRegisterClientResult(
                resultCode,
                clientId,
                locationCount,
                channelCount,
                activityTimeout,
                version,
                versionMin);
    }
}
