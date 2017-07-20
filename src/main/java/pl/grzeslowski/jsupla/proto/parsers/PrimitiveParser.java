package pl.grzeslowski.jsupla.proto.parsers;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;

final class PrimitiveParser {
    static int parseInt(byte[] bytes, int offset) {
        final StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; i--) {
            sb.append(binaryRepresentation(bytes[i]).replace(' ', '0'));
        }

        return MIN_VALUE + Integer.parseUnsignedInt(sb.toString(), 2);
    }

    private static String binaryRepresentation(byte bit) {
        return format("%8s", Integer.toBinaryString(bit & 0xFF));
    }
}
