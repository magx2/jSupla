package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaEvent;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public class SuplaEventDecoder implements ServerClientDecoder<SuplaEvent> {
    @Override
    public SuplaEvent decode(byte[] bytes, int offset) {
        final int event = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelId = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long durationMs = PrimitiveParser.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final int senderId = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int senderNameSize = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] senderName = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SENDER_NAME_MAXSIZE);

        return new SuplaEvent(event, channelId, durationMs, senderId, senderNameSize, senderName);
    }
}
