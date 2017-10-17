package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl.INSTANCE;

public class StringSerializerImplTest {
    @Test
    public void shouldSerializeString() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes("UTF-8");

        // when
        final byte[] bytes = INSTANCE.serialize(string);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test
    public void shouldSerializeStringWithGivenLength() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes("UTF-8");

        // when
        final byte[] bytes = INSTANCE.serialize(string, expectedBytes.length);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldShouldThrowIllegalArgumentExceptionIfLengthIsSmallerThatStringSize() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes("UTF-8");

        // when
        INSTANCE.serialize(string, expectedBytes.length - 1);
    }

    @Test
    public void shouldPutZerosIfLengthIsBiggerThanBytesLength() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final byte[] expectedBytes = string.getBytes("UTF-8");
        final int extendedLength = 10;

        // when
        final byte[] bytes = INSTANCE.serialize(string, expectedBytes.length + extendedLength);

        // then
        for (int i = expectedBytes.length; i < expectedBytes.length + extendedLength; i++) {
            assertThat(bytes[i]).isEqualTo((byte) 0);
        }
    }

    @Test
    public void shouldSerializePassword() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes("UTF-8");

        // when
        final byte[] bytes = INSTANCE.serializePassword(password, expectedBytes.length);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test
    public void shouldPutZerosInPasswordIfLengthIsBiggerThanBytesLength() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes("UTF-8");
        final int extendedLength = 10;

        // when
        final byte[] bytes = INSTANCE.serializePassword(password, expectedBytes.length + extendedLength);

        // then
        for (int i = expectedBytes.length; i < expectedBytes.length + extendedLength; i++) {
            assertThat(bytes[i]).isEqualTo((byte) 0);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldShouldThrowIllegalArgumentExceptionIfPasswordLengthIsSmallerThatStringSize() throws Exception {

        // given
        final String string = "a b c # € Œ ϻ ¥";
        final char[] password = string.toCharArray();
        final byte[] expectedBytes = string.getBytes("UTF-8");

        // when
        INSTANCE.serializePassword(password, expectedBytes.length - 1);
    }
}