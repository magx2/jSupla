package pl.grzeslowski.jsupla.protocol.api.decoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

public class TimerStateExtendedValueDecoder
        implements pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder<
                TimerStateExtendedValue> {
    public static final TimerStateExtendedValueDecoder INSTANCE =
            new TimerStateExtendedValueDecoder();

    @SuppressWarnings({"ConstantValue", "UnusedAssignment"})
    @Override
    public TimerStateExtendedValue decode(byte[] bytes, int offset) {
        // EV_TYPE_TIMER_STATE_V1_SEC uses remainingTimeS and EV_TYPE_TIMER_STATE_V1 uses ms
        // at this point I don't know which one I'm parsing,
        // so I will always parse `remainingTimeMs`
        Long remainingTimeMs = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        Long remainingTimeS = null;
        Long countdownEndsAt = null;
        offset += INT_SIZE;

        short[] targetValue =
                PrimitiveDecoder.INSTANCE.copyOfRangeByteUnsigned(
                        bytes, offset, offset + (SUPLA_CHANNELVALUE_SIZE * BYTE_SIZE));
        offset += SUPLA_CHANNELVALUE_SIZE * BYTE_SIZE;

        int senderId = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        long senderNameSize = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        byte[] senderName =
                PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                        bytes, offset, (offset + SUPLA_SENDER_NAME_MAXSIZE));
        offset += SUPLA_SENDER_NAME_MAXSIZE * BYTE_SIZE;

        return new TimerStateExtendedValue(
                remainingTimeMs,
                remainingTimeS,
                countdownEndsAt,
                targetValue,
                senderId,
                senderNameSize,
                senderName);
    }
}
