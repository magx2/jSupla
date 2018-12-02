package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelBDecoderImpl implements SuplaChannelBDecoder {
    public static final SuplaChannelBDecoderImpl INSTANCE = new SuplaChannelBDecoderImpl(
            pl.grzeslowski.jsupla.protocol.impl.decoders.SuplaChannelValueDecoderImpl.INSTANCE);
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    public SuplaChannelBDecoderImpl(SuplaChannelValueDecoder suplaChannelValueDecoder) {
        this.suplaChannelValueDecoder = requireNonNull(suplaChannelValueDecoder);
    }

    @Override
    public SuplaChannelB decode(byte[] bytes, int offset) {
        final byte eol = PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationId = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int func = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int altIcon = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long flags = PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final short protocolVersion = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte online = PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final SuplaChannelValue value = suplaChannelValueDecoder.decode(bytes, offset);
        offset += value.size();

        final long captionSize = PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaChannelB(
                eol,
                id,
                locationId,
                func,
                altIcon,
                flags,
                protocolVersion,
                online,
                value,
                captionSize,
                caption
        );
    }
}
