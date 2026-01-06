package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.EV_TYPE_TIMER_STATE_V1;

import java.time.Duration;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue;

class TimerMsecDecoder extends TimerAbstractDecoder {

    @Override
    protected Duration findRemaining(TimerStateExtendedValue timer) {
        return Duration.ofMillis(timer.remainingTimeMs());
    }

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(EV_TYPE_TIMER_STATE_V1);
    }
}
