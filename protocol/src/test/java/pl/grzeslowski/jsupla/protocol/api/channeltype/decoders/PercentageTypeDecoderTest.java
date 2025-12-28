package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

class PercentageTypeDecoderTest {
    private final PercentageTypeDecoder decoder = new PercentageTypeDecoder();

    @Test
    void shouldDecodeZeroPercent() {
        // given
        final byte[] bytes = new byte[] {0, 0, 0, 0, 0, 0, 0, 0};

        // when
        final PercentValue value = decoder.decode(bytes);

        // then
        assertThat(value.value()).isZero();
    }

    @Test
    void shouldDecodeFiftyPercent() {
        // given
        final byte[] bytes = new byte[] {50, 0, 0, 0, 0, 0, 0, 0};

        // when
        final PercentValue value = decoder.decode(bytes);

        // then
        assertThat(value.value()).isEqualTo(50);
    }

    @Test
    void shouldDecodeHundredPercent() {
        // given
        final byte[] bytes = new byte[] {100, 0, 0, 0, 0, 0, 0, 0};

        // when
        final PercentValue value = decoder.decode(bytes);

        // then
        assertThat(value.value()).isEqualTo(100);
    }

    @ParameterizedTest(name = "{index}: should set percentage value to 0 for (byte) {0}")
    @CsvSource({"-128", "-127", "-45", "-1", "101", "111", "127"})
    void outOfBounds(byte byteValue) {
        // given
        final byte[] bytes = new byte[] {byteValue, 0, 0, 0, 0, 0, 0, 0};

        // when
        final PercentValue value = decoder.decode(bytes);

        // then
        assertThat(value.value()).isZero();
    }
}
