package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

import java.time.Duration;

public class TimerSecChannelDecoder extends TimerAbstractChannelDecoder {
    @Override
    protected Duration findRemaining(TimerStateExtendedValue timer) {
        // using `remainingTimeMs` is correct!
        // TimerStateExtendedValueDecoder puts always value to `remainingTimeMs`,
        // because at the time of parsing it does not know if it's seconds or milliseconds
        return Duration.ofSeconds(timer.remainingTimeMs);
    }
}
