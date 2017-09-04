package pl.grzeslowski.jsupla;

import java.util.Collection;

import static java.lang.String.format;

public final class Preconditions {
    public static int min(int value, int min) {
        if (value < min) {
            throw new IllegalArgumentException(format("Given value %s is smaller than minimal value %s!", value, min));
        }
        return value;
    }

    public static long min(long value, long min) {
        if (value < min) {
            throw new IllegalArgumentException(format("Given value %s is smaller than minimal value %s!", value, min));
        }
        return value;
    }

    public static short min(short value, short min) {
        if (value < min) {
            throw new IllegalArgumentException(format("Given value %s is smaller than minimal value %s!", value, min));
        }
        return value;
    }

    public static short max(short value, short max) {
        if (value > max) {
            throw new IllegalArgumentException(format("Given value %s is bigger than maximal value %s!", value, max));
        }
        return value;
    }

    public static int max(int value, int max) {
        if (value > max) {
            throw new IllegalArgumentException(format("Given value %s is bigger than maximal value %s!", value, max));
        }
        return value;
    }

    public static long max(long value, long max) {
        if (value > max) {
            throw new IllegalArgumentException(format("Given value %s is bigger than maximal value %s!", value, max));
        }
        return value;
    }

    public static int size(int value, int min, int max) {
        return max(min(value, min), max);
    }

    public static <T> Collection<T> size(Collection<T> collection, int min, int max) {
        return sizeMax(sizeMin(collection, min), max);
    }

    public static <T extends CharSequence> T size(T collection, int min, int max) {
        return sizeMax(sizeMin(collection, min), max);
    }

    public static <T> T[] size(T[] collection, int min, int max) {
        return sizeMax(sizeMin(collection, min), max);
    }

    public static byte[] size(byte[] collection, int min, int max) {
        return sizeMax(sizeMin(collection, min), max);
    }

    public static byte[] size(byte[] collection, long min, long max) {
        return sizeMax(sizeMin(collection, min), max);
    }

    public static char[] size(char[] collection, int min, int max) {
        return sizeMax(sizeMin(collection, min), max);
    }

    public static int size(int value, int equalTo) {
        if (value != equalTo) {
            throw new IllegalArgumentException(format("Given value %s is not equal to %s!", value, equalTo));
        }
        return value;
    }

    public static short size(short value, short equalTo) {
        if (value != equalTo) {
            throw new IllegalArgumentException(format("Given value %s is not equal to %s!", value, equalTo));
        }
        return value;
    }

    public static <T> Collection<T> sizeMax(Collection<T> collection, int max) {
        final int size = collection.size();
        if (size > max) {
            throw new IllegalArgumentException(format("Collection size %s is too big, max %s!", size, max));
        }
        return collection;
    }

    public static <T extends CharSequence> T sizeMax(T collection, int max) {
        final int size = collection.length();
        if (size > max) {
            throw new IllegalArgumentException(format("CharSequence size %s is too big, max %s!", size, max));
        }
        return collection;
    }

    public static <T> T[] sizeMax(T[] collection, int max) {
        final int size = collection.length;
        if (size > max) {
            throw new IllegalArgumentException(format("Collection size %s is too big, max %s!", size, max));
        }
        return collection;
    }

    public static byte[] sizeMax(byte[] collection, int max) {
        final int size = collection.length;
        if (size > max) {
            throw new IllegalArgumentException(format("Collection size %s is too big, max %s!", size, max));
        }
        return collection;
    }

    public static byte[] sizeMax(byte[] collection, long max) {
        final int size = collection.length;
        if (size > max) {
            throw new IllegalArgumentException(format("Collection size %s is too big, max %s!", size, max));
        }
        return collection;
    }

    public static char[] sizeMax(char[] collection, long max) {
        final int size = collection.length;
        if (size > max) {
            throw new IllegalArgumentException(format("Collection size %s is too big, max %s!", size, max));
        }
        return collection;
    }

    public static int sizeMax(int value, long max) {
        if (value > max) {
            throw new IllegalArgumentException(format("Given value %s is too big, max %s!", value, max));
        }
        return value;
    }

    public static short sizeMax(short value, short max) {
        if (value > max) {
            throw new IllegalArgumentException(format("Given value %s is too big, max %s!", value, max));
        }
        return value;
    }

    public static <T> Collection<T> sizeMin(Collection<T> collection, int min) {
        final int size = collection.size();
        if (size < min) {
            throw new IllegalArgumentException(format("Collection size %s is too small, min %s!", size, min));
        }
        return collection;
    }

    public static <T extends CharSequence> T sizeMin(T collection, int min) {
        final int size = collection.length();
        if (size < min) {
            throw new IllegalArgumentException(format("CharSequence size %s is too small, min %s!", size, min));
        }
        return collection;
    }

    public static <T> T[] sizeMin(T[] collection, int min) {
        final int size = collection.length;
        if (size < min) {
            throw new IllegalArgumentException(format("Collection size %s is too small, min %s!", size, min));
        }
        return collection;
    }

    public static byte[] sizeMin(byte[] collection, int min) {
        final int size = collection.length;
        if (size < min) {
            throw new IllegalArgumentException(format("Collection size %s is too small, min %s!", size, min));
        }
        return collection;
    }

    public static byte[] sizeMin(byte[] collection, long min) {
        final int size = collection.length;
        if (size < min) {
            throw new IllegalArgumentException(format("Collection size %s is too small, min %s!", size, min));
        }
        return collection;
    }

    public static char[] sizeMin(char[] collection, int min) {
        final int size = collection.length;
        if (size < min) {
            throw new IllegalArgumentException(format("Collection size %s is too small, min %s!", size, min));
        }
        return collection;
    }

    public static byte[] checkArrayLength(byte[] bytes, int length) {
        if (bytes.length != length) {
            throw new IllegalArgumentException(
                    format("Length of array should be %s but was %s!", length, bytes.length));
        }
        return bytes;
    }

    public static char[] checkArrayLength(char[] bytes, int length) {
        if (bytes.length != length) {
            throw new IllegalArgumentException(
                    format("Length of array should be %s but was %s!", length, bytes.length));
        }
        return bytes;
    }

    public static <T> T[] checkArrayLength(T[] array, int length) {
        if (array.length != length) {
            throw new IllegalArgumentException(
                    format("Length of array should be %s but was %s!", length, array.length));
        }
        return array;
    }

    public static int byteSize(int byteValue) {
        return size(byteValue, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    public static int unsignedByteSize(int unsignedByteValue) {
        return size(unsignedByteValue, 0, 255);
    }

    private Preconditions() {

    }
}
