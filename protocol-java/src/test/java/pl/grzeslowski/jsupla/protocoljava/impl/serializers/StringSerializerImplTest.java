package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl.INSTANCE;

public class StringSerializerImplTest {
    @Test
    public void shouldSerializeString() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes(UTF_8);

        // when
        final byte[] bytes = INSTANCE.serialize(string);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test
    public void shouldSerializeStringWithGivenLength() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes(UTF_8);

        // when
        final byte[] bytes = INSTANCE.serialize(string, expectedBytes.length);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldShouldThrowIllegalArgumentExceptionIfLengthIsSmallerThatStringSize() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes(UTF_8);

        // when
        INSTANCE.serialize(string, expectedBytes.length - 1);
    }

    @Test
    public void shouldPutZerosIfLengthIsBiggerThanBytesLength() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes(UTF_8);
        final int extendedLength = 10;

        // when
        final byte[] bytes = INSTANCE.serialize(string, expectedBytes.length + extendedLength);

        // then
        for (int i = expectedBytes.length; i < expectedBytes.length + extendedLength; i++) {
            assertThat(bytes[i]).isEqualTo((byte) 0);
        }
    }

    @Test
    public void shouldSerializePassword() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes(UTF_8);

        // when
        final byte[] bytes = INSTANCE.serializePassword(password, expectedBytes.length);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test
    public void shouldNotClearInputArray() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes(UTF_8);

        // when
        INSTANCE.serializePassword(password, expectedBytes.length);

        // then
        assertThat(password).isEqualTo(string.toCharArray());
    }

    @Test
    public void shouldPutZerosInPasswordIfLengthIsBiggerThanBytesLength() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes(UTF_8);
        final int extendedLength = 10;

        // when
        final byte[] bytes = INSTANCE.serializePassword(password, expectedBytes.length + extendedLength);

        // then
        for (int i = expectedBytes.length; i < expectedBytes.length + extendedLength; i++) {
            assertThat(bytes[i]).isEqualTo((byte) 0);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldShouldThrowIllegalArgumentExceptionIfPasswordLengthIsSmallerThatStringSize() {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes(UTF_8);

        // when
        INSTANCE.serializePassword(password, expectedBytes.length - 1);
    }

    @Test
    public void shouldParseHexString() {

        // given
        String hex = "685A7A7079677A575700000000000000";
        byte[] expectedBytes = new byte[]{104, 90, 122, 112, 121, 103, 122, 87, 87, 0, 0, 0, 0, 0, 0, 0};

        // when
        byte[] bytes = INSTANCE.serializeHexString(hex);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }
}