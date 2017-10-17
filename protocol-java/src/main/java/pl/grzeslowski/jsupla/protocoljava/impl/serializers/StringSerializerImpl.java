package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

import static java.lang.System.arraycopy;
import static java.nio.charset.StandardCharsets.UTF_8;

public class StringSerializerImpl implements StringSerializer {
    public static final StringSerializerImpl INSTANCE = new StringSerializerImpl();

    @Override
    public byte[] serialize(final String string, final int length) {
        final byte[] bytes = new byte[length];
        final byte[] stringBytes = getBytesFromString(string);
        Preconditions.max(stringBytes.length, length);
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
        Preconditions.max(bytes.length, length);
        final byte[] passwordBytes = new byte[length];
        arraycopy(bytes, 0, passwordBytes, 0, bytes.length);

        return passwordBytes;
    }
}
