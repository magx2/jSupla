package pl.grzeslowski.jsupla.protocol.api;

import java.math.BigInteger;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public interface JavaConsts {
    public static final int BYTE_SIZE = 1;
    public static final int CHAR_SIZE = BYTE_SIZE;
    public static final int SHORT_SIZE = 2 * BYTE_SIZE;
    public static final int INT_SIZE = 4 * BYTE_SIZE;
    public static final int LONG_SIZE = 2 * INT_SIZE;
    public static final int DOUBLE_SIZE = 8;

    public static final int UNSIGNED_BYTE_MAX = 255;
    public static final int UNSIGNED_SHORT_MAX = 65535;
    public static final long UNSIGNED_INT_MAX = 4294967295L;
    public static final BigInteger UNSIGNED_LONG_MAX = new BigInteger("18446744073709551615");

    public static final BigInteger UNSIGNED_LONG_MASK =
            BigInteger.ONE.shiftLeft(Long.SIZE).subtract(BigInteger.ONE);

    public static int unionSize(int... sizes) {
        int max = sizes[0];
        for (int size : sizes) {
            max = Math.max(max, size);
        }
        return max;
    }

    public static <T extends ProtoWithSize> int arraySize(T[] array) {
        int size = 0;
        for (T proto : array) {
            size += proto.protoSize();
        }
        return size;
    }
}
