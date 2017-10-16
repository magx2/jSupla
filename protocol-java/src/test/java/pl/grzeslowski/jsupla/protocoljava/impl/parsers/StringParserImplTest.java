package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.impl.parsers.StringParserImpl.INSTANCE;

public class StringParserImplTest {
    @Test
    public void shouldShouldParseUtf8String() throws Exception {

        // given
        final String expectedString = "a b c # € Œ ϻ ¥";
        final byte[] bytes = expectedString.getBytes("UTF-8");

        // when
        final String string = INSTANCE.parse(bytes);

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenBytesAreNull() {
        INSTANCE.parse(null);
    }

    @Test
    public void shouldReturnEmptyWhenGivenBytesHAs0Length() throws Exception {

        // given
        final byte[] bytes = new byte[0];

        // when
        final String string = INSTANCE.parse(bytes);

        // then
        assertThat(string).isEmpty();
    }

    @Test
    public void shouldShouldParsePassword() throws Exception {

        // given
        final byte[] bytes = (
                                     "a" +
                                             "b" +
                                             "c" +
                                             "#" +
                                             "\u20AC" + // €
                                             "\u0152" + // Œ
                                             "\u03FB" + // ϻ
                                             "\u00A5" // ¥
        ).getBytes("UTF-8");

        // when
        final char[] password = INSTANCE.parsePassword(bytes);

        // then
        assertThat(password[0]).isEqualTo('a');
        assertThat(password[1]).isEqualTo('b');
        assertThat(password[2]).isEqualTo('c');
        assertThat(password[3]).isEqualTo('#');
        assertThat(password[4]).isEqualTo('\u20AC'); // €
        assertThat(password[5]).isEqualTo('\u0152'); // Œ
        assertThat(password[6]).isEqualTo('\u03FB'); // ϻ
        assertThat(password[7]).isEqualTo('\u00A5'); // ¥
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenBytesAreNullPassword() {
        INSTANCE.parsePassword(null);
    }

    @Test
    public void shouldReturnEmptyCharsWhenGivenBytesHas0Length() throws Exception {

        // given
        final byte[] bytes = new byte[0];

        // when
        final char[] password = INSTANCE.parsePassword(bytes);

        // then
        assertThat(password).isEmpty();
    }

    @Test
    public void shouldParseHex() throws Exception {

        // given
        final byte[] bytes = new byte[]{
                -62, // C2
                25, // 19
                127, // 7F
                -1, //FF
                -128, // 80
                -35, // DD
                -5, // FB
                5, // 05
                -62, // C2
                25, // 19
                127, // 7F
                -1, // FF
                -128, // 80
                -35, // DD
                -5, // FB
                5 // 05
        };

        // when
        final String hex = INSTANCE.parseHexString(bytes);

        // then
        //noinspection SpellCheckingInspection
        assertThat(hex).isEqualTo("C2197FFF80DDFB05C2197FFF80DDFB05");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfBytesLengthIsTooShort() {

        // given
        final byte[] bytes = new byte[15];

        // when
        INSTANCE.parseHexString(bytes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfBytesLengthIsTooLong() {

        // given
        final byte[] bytes = new byte[17];

        // when
        INSTANCE.parseHexString(bytes);
    }
}