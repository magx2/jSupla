package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaNewValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaNewValueDecoderImpl implements SuplaNewValueDecoder {
    @Override
    public SuplaNewValue decode(final byte[] bytes, int offset) {

        final int id = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte target = INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaNewValue(id, target, value);
    }
}
