package pl.grzeslowski.jsupla.protocol.impl.decoders;

import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelExtendedValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@RequiredArgsConstructor
public class SuplaChannelExtendedValueDecoderImpl implements Decoder<SuplaChannelExtendedValue> {
    public static final SuplaChannelExtendedValueDecoderImpl INSTANCE = new SuplaChannelExtendedValueDecoderImpl();
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelExtendedValueDecoderImpl() {
        this(PrimitiveDecoderImpl.INSTANCE);
    }

    @Override
    public SuplaChannelExtendedValue decode(byte[] bytes, int offset) {
        byte type = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        long size = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        byte[] value = primitiveDecoder.copyOfRange(bytes, offset, (int) (offset + size));

        return new SuplaChannelExtendedValue(type, size, value);
    }
}
