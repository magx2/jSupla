package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;

public class TimerSecChannelDecoderTest {
    private final TimerSecChannelDecoder decoder = new TimerSecChannelDecoder();

    @Test
    public void shouldInterpretRemainingTimeInSeconds() {
        // given
        byte[] payload = new TimerTestPayloadBuilder().withRemainingMilliseconds(12).build();

        // when
        TimerValue value = decoder.decode(payload);

        // then
        assertThat(value.getRemaining()).isEqualTo(Duration.ofSeconds(12));
    }
}
