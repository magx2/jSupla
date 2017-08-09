package pl.grzeslowski.jsupla.protocol;

import static java.lang.String.format;

public final class ProtoPreconditions {
    public static byte[] checkArrayLength(byte[] bytes, int length) {
        if (bytes.length != length) {
            throw new IllegalArgumentException(format("Length of array should be %s but was %s!", length, bytes.length));
        }
        return bytes;
    }

    public static char[] checkArrayLength(char[] bytes, int length) {
        if (bytes.length != length) {
            throw new IllegalArgumentException(format("Length of array should be %s but was %s!", length, bytes.length));
        }
        return bytes;
    }

    public static <T> T[] checkArrayLength(T[] array, int length) {
        if (array.length != length) {
            throw new IllegalArgumentException(format("Length of array should be %s but was %s!", length, array.length));
        }
        return array;
    }

    private ProtoPreconditions() {
    }
}
