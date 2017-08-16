package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public final class SuplaChannelNewValueDecoderImpl implements SuplaChannelNewValueDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValue decode(byte[] bytes, int offset) {
        final byte channelId = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValue(channelId, value);
    }
}
