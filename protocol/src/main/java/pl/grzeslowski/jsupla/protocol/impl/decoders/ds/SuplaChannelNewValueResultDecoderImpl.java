package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaChannelNewValueResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelNewValueResultDecoderImpl implements DeviceServerDecoder<SuplaChannelNewValueResult> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueResultDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValueResult decode(byte[] bytes, int offset) {
        final short channelNumber = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int senderId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte success = primitiveDecoder.parseByte(bytes, offset);

        return new SuplaChannelNewValueResult(channelNumber, senderId, success);
    }
}
