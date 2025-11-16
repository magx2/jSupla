package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;

public class TimerMsecChannelDecoderTest {
    private final TimerMsecChannelDecoder decoder = new TimerMsecChannelDecoder();

    @Test
    public void shouldInterpretRemainingTimeInMilliseconds() {
        // given
        byte[] payload = new TimerTestPayloadBuilder().withRemainingMilliseconds(4321).build();

        // when
        TimerValue value = decoder.decode(payload);

        // then
        assertThat(value.remaining()).isEqualTo(Duration.ofMillis(4321));
    }
}
