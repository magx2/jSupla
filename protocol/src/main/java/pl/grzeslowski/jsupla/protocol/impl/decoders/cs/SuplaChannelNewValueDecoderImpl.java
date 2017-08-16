package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.cs.ClientServerDecoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValue;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public final class SuplaChannelNewValueDecoderImpl implements ClientServerDecoder<SuplaChannelNewValue> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValue decode(byte[] bytes, int offset) {
        final byte channelId = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValue(channelId, value);
    }
}
