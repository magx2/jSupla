package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaNewValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaNewValueDecoderImpl implements SuplaNewValueDecoder {
    public static final SuplaNewValueDecoderImpl INSTANCE = new SuplaNewValueDecoderImpl();

    @Override
    public SuplaNewValue decode(final byte[] bytes, int offset) {

        final int id = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte target = PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = PrimitiveDecoderImpl.INSTANCE.copyOfRange(
            bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaNewValue(id, target, value);
    }
}
