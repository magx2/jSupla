package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseHexString;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseIpv4;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseMac;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parsePassword;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseString;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.toSignedInt;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.toUnsignedByte;

import org.junit.jupiter.api.Test;

class ProtocolHelpersTest {
    @Test
    void shouldConvert0LTo0() {
        assertThat(toSignedInt(0L)).isZero();
    }

    @Test
    void shouldConvertPositiveValue() {
        assertThat(toSignedInt(123L)).isEqualTo(123);
    }

    @Test
    void shouldConvertIntegerMaxValue() {
        assertThat(toSignedInt(Integer.MAX_VALUE)).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void shouldConvertToNegative() {
        assertThat(toSignedInt(2147483648L)).isEqualTo(Integer.MIN_VALUE);
    }

    @Test
    void shouldConvertMaxUnsignedInt() {
        // 0xFFFFFFFFL
        assertThat(toSignedInt(4294967295L)).isEqualTo(-1);
    }

    @Test
    void shouldThrowExceptionForNegativeInput() {
        assertThatThrownBy(() -> toSignedInt(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value -1 is smaller than minimal value 0!");
    }

    @Test
    void shouldThrowExceptionForTooLargeInput() {
        assertThatThrownBy(() -> toSignedInt(4294967296L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value 4294967296 is bigger than maximal value 4294967295!");
    }

    @Test
    void shouldParseStringIgnoringTrailingZeros() {
        byte[] bytes = {80, 65, 83, 83, 0, 1, 2};

        assertThat(parseString(bytes)).isEqualTo("PASS");
    }

    @Test
    void shouldParsePassword() {
        var password = parsePassword("secret".getBytes());

        assertThat(password).containsExactly('s', 'e', 'c', 'r', 'e', 't');
    }

    @Test
    void shouldParseHexString() {
        byte[] payload =
                new byte[] {
                    0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
                    0x10, 0x32, 0x54, 0x76, (byte) 0x98, (byte) 0xBA, (byte) 0xDC, (byte) 0xFE
                };

        assertThat(parseHexString(payload)).isEqualTo("0123456789ABCDEF1032547698BADCFE");
    }

    @Test
    void shouldValidateHexStringLength() {
        assertThatThrownBy(() -> parseHexString(new byte[] {1, 2, 3}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Length of array should be 16 but was 3!");
    }

    @Test
    void shouldParseIpv4() {
        assertThat(parseIpv4(0xC0A80001L)).isEqualTo("1.0.168.192");
    }

    @Test
    void shouldParseMac() {
        assertThat(parseMac(new short[] {0x00, 0x1A, (short) 0xFF, 0x55, 0x10, 0x20}))
                .isEqualTo("00:1A:FF:55:10:20");
    }

    @Test
    void shouldRejectInvalidMac() {
        assertThatThrownBy(() -> parseMac(new short[] {1, 2}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("MAC address must be a 6-element short array.");
    }

    @Test
    void shouldConvertUnsignedByte() {
        assertThat(toUnsignedByte((byte) 0xFF)).isEqualTo((short) 255);
    }
}
