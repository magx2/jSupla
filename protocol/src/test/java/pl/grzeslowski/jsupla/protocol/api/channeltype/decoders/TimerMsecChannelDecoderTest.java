package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;

class TimerMsecChannelDecoderTest {
    private final TimerMsecDecoder decoder = new TimerMsecDecoder();

    @Test
    void shouldInterpretRemainingTimeInMilliseconds() {
        // given
        byte[] payload = new TimerTestPayloadBuilder().withRemainingMilliseconds(4321).build();

        // when
        TimerValue value = decoder.decode(payload);

        // then
        assertThat(value.remaining()).isEqualTo(Duration.ofMillis(4321));
    }
}
