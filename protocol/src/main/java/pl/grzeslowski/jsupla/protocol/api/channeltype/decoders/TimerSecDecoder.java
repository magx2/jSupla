package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.EV_TYPE_TIMER_STATE_V1_SEC;

import java.time.Duration;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

class TimerSecDecoder extends TimerAbstractDecoder {
    @Override
    protected Duration findRemaining(TimerStateExtendedValue timer) {
        // using `remainingTimeMs` is correct!
        // TimerStateExtendedValueDecoder puts always value to `remainingTimeMs`,
        // because at the time of parsing it does not know if it's seconds or milliseconds
        return Duration.ofSeconds(timer.remainingTimeMs());
    }

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(EV_TYPE_TIMER_STATE_V1_SEC);
    }
}
