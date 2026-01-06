package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;

class TimerSecChannelDecoderTest {
    private final TimerSecDecoder decoder = new TimerSecDecoder();

    @Test
    void shouldInterpretRemainingTimeInSeconds() {
        // given
        byte[] payload = new TimerTestPayloadBuilder().withRemainingMilliseconds(12).build();

        // when
        TimerValue value = decoder.decode(payload);

        // then
        assertThat(value.remaining()).isEqualTo(Duration.ofSeconds(12));
    }
}
