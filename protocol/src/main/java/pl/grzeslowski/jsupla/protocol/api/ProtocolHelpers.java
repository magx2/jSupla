package pl.grzeslowski.jsupla.protocol.api;

import lombok.val;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import static java.nio.charset.StandardCharsets.UTF_8;
import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;

public interface ProtocolHelpers {
    static String parseString(byte[] bytes) {
        int length = bytes.length;
        for (int idx = 0; idx < length; idx++) {
            if (bytes[idx] == 0) {
                length = idx;
                break;
            }
        }
        return new String(bytes, 0, length, UTF_8);
    }

    /**
     * TODO can be optimized!!!.
     *
     * @see <a href="https://stackoverflow.com/a/4932112/1819402">https://stackoverflow.com/a/4932112/1819402</a>
     */
    static char[] parsePassword(final byte[] utfBytes) {
        final CharBuffer charBuffer = UTF_8.decode(ByteBuffer.wrap(utfBytes)); // also decode to String
        return charBuffer.array();
    }

    static String parseHexString(byte[] bytes) {
        val HEX_LENGTH = 16;
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
