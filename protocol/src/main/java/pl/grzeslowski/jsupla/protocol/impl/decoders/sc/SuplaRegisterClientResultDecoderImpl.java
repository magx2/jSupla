package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sc.ServerClientDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaRegisterClientResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterClientResultDecoderImpl implements ServerClientDecoder<SuplaRegisterClientResult> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaRegisterClientResultDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaRegisterClientResult decode(byte[] bytes, int offset) {
        final int resultCode = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int clientId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationCount = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelCount = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final short activityTimeout = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short version = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short versionMin = primitiveDecoder.parseUnsignedByte(bytes, offset);

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
