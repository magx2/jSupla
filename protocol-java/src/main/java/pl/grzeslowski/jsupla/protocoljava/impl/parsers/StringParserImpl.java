package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import static java.nio.charset.StandardCharsets.UTF_8;
import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;

public class StringParserImpl implements StringParser {
    public static final StringParserImpl INSTANCE = new StringParserImpl();
    private static final int HEX_LENGTH = 16;

    @Override
    public String parse(byte[] bytes) {
        return parseGenericString(bytes, 0, bytes.length, "UTF-8");
    }

    @SuppressWarnings("SameParameterValue")
    private String parseGenericString(byte[] bytes, int offset, int length, String charset) {
        sizeMin(bytes, offset + length);
        int end = length;
        for (int i = offset; i < length; i++) {
            if (bytes[i] == (byte) 0) {
                end = i - offset;
                break;
            }
        }
        try {
            return new String(bytes, offset, end, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO can be optimized!!!.
     *
     * @see <a href="https://stackoverflow.com/a/4932112/1819402">https://stackoverflow.com/a/4932112/1819402</a>
     */
    @Override
    public char[] parsePassword(final byte[] utfBytes) {
        final CharBuffer charBuffer = UTF_8.decode(ByteBuffer.wrap(utfBytes)); // also decode to String
        return charBuffer.array();
    }

    @Override
    public String parseHexString(byte[] bytes) {
        checkArrayLength(bytes, HEX_LENGTH);
        return String.format("%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X",
            bytes[0],
            bytes[1],
            bytes[2],
            bytes[3],
            bytes[4],
            bytes[5],
            bytes[6],
            bytes[7],
            bytes[8],
            bytes[9],
            bytes[10],
            bytes[11],
            bytes[12],
            bytes[13],
            bytes[14],
            bytes[15]
        );
    }
}
