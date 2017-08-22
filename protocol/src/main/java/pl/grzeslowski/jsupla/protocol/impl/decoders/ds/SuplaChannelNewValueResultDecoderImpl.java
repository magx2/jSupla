package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaChannelNewValueResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelNewValueResultDecoderImpl implements SuplaChannelNewValueResultDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueResultDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValueResult decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaChannelNewValueResult.SIZE);

        final short channelNumber = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int senderId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte success = primitiveDecoder.parseByte(bytes, offset);

        return new SuplaChannelNewValueResult(channelNumber, senderId, success);
    }
}
