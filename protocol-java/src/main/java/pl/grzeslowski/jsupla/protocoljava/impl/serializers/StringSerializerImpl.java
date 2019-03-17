package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static java.nio.charset.StandardCharsets.UTF_8;
import static pl.grzeslowski.jsupla.Preconditions.max;

public class StringSerializerImpl implements StringSerializer {
    public static final StringSerializerImpl INSTANCE = new StringSerializerImpl();
    private static final int HEX_STRING_SUPPORTED_LENGTH = 32;

    @Override
    public byte[] serialize(final String string, final int length) {
        final byte[] bytes = new byte[length];
        final byte[] stringBytes = getBytesFromString(string);
        if (stringBytes.length > length) {
            throw new IllegalArgumentException(format(
                    "String [%s] has too many bytes [%s] to fit in byte array of size [%s]",
                    string, stringBytes.length, length
            ));
        }
        arraycopy(stringBytes, 0, bytes, 0, stringBytes.length);
        return bytes;
    }

    @Override
    public byte[] serialize(final String string) {
        return serialize(string, getBytesFromString(string).length);
    }

    private byte[] getBytesFromString(final String string) {
        return string.getBytes(UTF_8);
    }

    /**
     * Took from <a href="https://stackoverflow.com/a/9670279/1819402">Stack Overflow</a>.
     */
    @Override
    public byte[] serializePassword(final char[] password, final int length) {
        CharBuffer charBuffer = CharBuffer.wrap(password);
        ByteBuffer byteBuffer = UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data

        // code to make byte array as big as length
        max(bytes.length, length);
        final byte[] passwordBytes = new byte[length];
        arraycopy(bytes, 0, passwordBytes, 0, bytes.length);

        return passwordBytes;
    }

    @Override
    public byte[] serializeHexString(String hex) {
        if (hex.length() != HEX_STRING_SUPPORTED_LENGTH) {
            throw new IllegalArgumentException(format(
                    "This length of string %s (%s) is not supported. Only length that is supported is %s",
                    hex, hex.length(), HEX_STRING_SUPPORTED_LENGTH));
        }
        byte[] bytes = new byte[16];
        for (int i = 0; i < hex.length() - 1; i = i + 2) {
            char first = hex.charAt(i);
            char second = hex.charAt(i + 1);
            int hexValue = Integer.parseInt("" + first + second, 16);
            bytes[i / 2] = (byte) hexValue;
        }
        return bytes;
    }
}
