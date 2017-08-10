package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaEvent;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public final class SuplaEventDecoder implements ServerClientDecoder<SuplaEvent> {
    @Override
    public SuplaEvent decode(byte[] bytes, int offset) {
        final int event = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long durationMs = PrimitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final int senderId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int senderNameSize = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] senderName = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SENDER_NAME_MAXSIZE);

        return new SuplaEvent(event, channelId, durationMs, senderId, senderNameSize, senderName);
    }
}
