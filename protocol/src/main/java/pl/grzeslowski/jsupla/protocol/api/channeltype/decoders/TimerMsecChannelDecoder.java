package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

import java.time.Duration;

public class TimerMsecChannelDecoder extends TimerAbstractChannelDecoder {

    @Override
    protected Duration findRemaining(TimerStateExtendedValue timer) {
        return Duration.ofMillis(timer.remainingTimeMs);
    }
}
