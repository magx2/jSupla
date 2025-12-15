package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

class TimerAbstractChannelDecoderTest {
    private final TimerTestPayloadBuilder builder = new TimerTestPayloadBuilder();

    @Test
    void shouldConvertTargetValuesBySubtractingOffset() {
        // given
        short[] target = new short[ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        for (int i = 0; i < target.length; i++) {
            target[i] = (short) (127 + i);
        }
        byte[] payload =
                builder.withRemainingMilliseconds(1_000L).withTarget(toBytes(target)).build();

        // when
        TestTimerDecoder decoder = new TestTimerDecoder();
        TimerValue result = decoder.decode(payload);

        // then
        assertThat(result.targetValue()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7);
    }

    private byte[] toBytes(short[] shorts) {
        byte[] bytes = new byte[shorts.length];
        for (int i = 0; i < shorts.length; i++) {
            bytes[i] = (byte) shorts[i];
        }
        return bytes;
    }

    private static class TestTimerDecoder extends TimerAbstractChannelDecoder {
        @Override
        protected Duration findRemaining(
                pl.grzeslowski.jsupla.protocol.api.structs.TimerStateExtendedValue timer) {
            return Duration.ZERO;
        }
    }
}
