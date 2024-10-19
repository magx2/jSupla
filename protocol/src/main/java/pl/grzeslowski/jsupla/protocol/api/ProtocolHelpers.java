package pl.grzeslowski.jsupla.protocol.api;

import lombok.val;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.checkArrayLength;

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
     * @param utfBytes utf bytes
     * @return given value
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

    public static String parseIpv4(long ip) {
        return String.format("%s.%s.%s.%s",
            (ip & 0xFF),
            ((ip >> 8) & 0xFF),
            ((ip >> 16) & 0xFF),
            ((ip >> 24) & 0xFF));
    }

    public static String parseMac(short[] mac) {
        if (mac == null || mac.length != 6) {
            throw new IllegalArgumentException("MAC address must be a 6-element short array.");
        }

        // Convert each short to unsigned byte and format it as a two-digit hex string
        return IntStream.range(0, mac.length)
            .mapToObj(i -> String.format("%02X", mac[i] & 0xFF)) // & 0xFF ensures unsigned byte
            .collect(joining(":")); // Join with ":"
    }
}
