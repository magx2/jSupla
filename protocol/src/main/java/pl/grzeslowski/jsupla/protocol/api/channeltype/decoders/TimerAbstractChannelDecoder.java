package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimerStateExtendedValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

import java.time.Duration;

import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseString;

abstract class TimerAbstractChannelDecoder implements Decoder<TimerValue> {
    @Override
    public TimerValue decode(byte[] bytes, int offset) {
        val timer = TimerStateExtendedValueDecoder.INSTANCE.decode(bytes, offset);

        val targetValue = new byte[timer.targetValue.length];
        for (int i = 0; i < timer.targetValue.length; i++) {
            targetValue[i] = (byte) (timer.targetValue[i] - 127);
        }

        return new TimerValue(
            findRemaining(timer),
            targetValue,
            timer.senderId,
            parseString(timer.senderName));
    }

    protected abstract Duration findRemaining(TimerStateExtendedValue timer);
}
