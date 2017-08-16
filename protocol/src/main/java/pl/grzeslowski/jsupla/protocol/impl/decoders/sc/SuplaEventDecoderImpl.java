package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaEvent;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public final class SuplaEventDecoderImpl implements ServerClientDecoder<SuplaEvent> {
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

        final byte[] senderName = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SENDER_NAME_MAXSIZE);

        return new SuplaEvent(event, channelId, durationMs, senderId, senderNameSize, senderName);
    }
}