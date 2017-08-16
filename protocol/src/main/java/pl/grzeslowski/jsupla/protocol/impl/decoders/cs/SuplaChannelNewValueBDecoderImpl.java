package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.cs.ClientServerDecoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValueB;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelNewValueBDecoderImpl implements ClientServerDecoder<SuplaChannelNewValueB> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueBDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValueB decode(byte[] bytes, int offset) {
        final int channelId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValueB(channelId, value);
    }
}
