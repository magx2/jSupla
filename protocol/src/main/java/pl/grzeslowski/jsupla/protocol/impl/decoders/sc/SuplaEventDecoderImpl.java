package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaEventDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public final class SuplaEventDecoderImpl implements SuplaEventDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaEventDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaEvent decode(byte[] bytes, int offset) {
        final int event = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long durationMs = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final int senderId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int senderNameSize = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] senderName = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_SENDER_NAME_MAXSIZE);

        return new SuplaEvent(event, channelId, durationMs, senderId, senderNameSize, senderName);
    }
}
