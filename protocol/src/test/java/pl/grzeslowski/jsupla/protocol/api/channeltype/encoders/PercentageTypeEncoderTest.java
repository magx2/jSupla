package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

class PercentageTypeEncoderTest {
    private final PercentageTypeEncoder encoder = new PercentageTypeEncoder();

    @Test
    void shouldEncodeZeroPercent() {
        // given
        final PercentValue value = new PercentValue(0);

        // when
        final byte[] bytes = encoder.encode(value);

        // then
        assertThat(bytes).hasSize(8);
        assertThat(bytes[0]).isEqualTo((byte) 0);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
        assertThat(bytes[4]).isEqualTo((byte) 0);
        assertThat(bytes[5]).isEqualTo((byte) 0);
        assertThat(bytes[6]).isEqualTo((byte) 0);
        assertThat(bytes[7]).isEqualTo((byte) 0);
    }

    @Test
    void shouldEncodeFiftyPercent() {
        // given
        final PercentValue value = new PercentValue(50);

        // when
        final byte[] bytes = encoder.encode(value);

        // then
        assertThat(bytes).hasSize(8);
        assertThat(bytes[0]).isEqualTo((byte) 50);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
        assertThat(bytes[4]).isEqualTo((byte) 0);
        assertThat(bytes[5]).isEqualTo((byte) 0);
        assertThat(bytes[6]).isEqualTo((byte) 0);
        assertThat(bytes[7]).isEqualTo((byte) 0);
    }

    @Test
    void shouldEncodeHundredPercent() {
        // given
        final PercentValue value = new PercentValue(100);

        // when
        final byte[] bytes = encoder.encode(value);

        // then
        assertThat(bytes).hasSize(8);
        assertThat(bytes[0]).isEqualTo((byte) 100);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
        assertThat(bytes[4]).isEqualTo((byte) 0);
        assertThat(bytes[5]).isEqualTo((byte) 0);
        assertThat(bytes[6]).isEqualTo((byte) 0);
        assertThat(bytes[7]).isEqualTo((byte) 0);
    }
}
