package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import java.time.Duration;
import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

public class TimerMsecChannelDecoder extends TimerAbstractChannelDecoder {

    @Override
    protected Duration findRemaining(TimerStateExtendedValue timer) {
        return Duration.ofMillis(timer.remainingTimeMs);
    }
}
