package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB.SIZE;

public final class SuplaChannelNewValueBDecoderImpl implements SuplaChannelNewValueBDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueBDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValueB decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SIZE);

        final int channelId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValueB(channelId, value);
    }
}
