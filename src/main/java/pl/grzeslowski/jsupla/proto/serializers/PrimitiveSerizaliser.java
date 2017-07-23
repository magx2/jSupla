package pl.grzeslowski.jsupla.proto.serializers;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

final class PrimitiveSerizaliser {
    public static void putUnsignedInt(int value, byte[] bytes, int offset) {
        if (bytes.length - offset < INT_SIZE) {
            throw new IllegalArgumentException(format("Bytes array has length %s and is not sufficient to put here int (%s) with offset %s",
                    bytes.length, INT_SIZE, offset));
        }
    }
}
