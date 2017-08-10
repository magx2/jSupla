package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaRegisterClientResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterClientResultDecoder implements ServerClientDecoder<SuplaRegisterClientResult> {
    @Override
    public SuplaRegisterClientResult decode(byte[] bytes, int offset) {
        final int resultCode = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int clientId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationCount = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelCount = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final short activityTimeout = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short version = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short versionMin = PrimitiveDecoder.parseUnsignedByte(bytes, offset);

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
