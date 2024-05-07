package pl.grzeslowski.jsupla.protocol.api;

import java.util.Collection;
import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class Preconditions {
    public static int min(int value, int min) {
        if (value < min) {
            throw new IllegalArgumentException(format("Given value %s is smaller than minimal value %s!", value, min));
        }
        return value;
    }

    public static short min(short value, int min) {
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

    public static byte min(byte value, byte min) {
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

    public static short size(short value, short min, short max) {
        return max(min(value, min), max);
    }

    public static int size(int value, int min, int max) {
        return max(min(value, min), max);
    }

    public static long size(long value, long min, long max) {
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

    public static <T> List<T> sizeMin(List<T> collection, int min) {
        final int size = collection.size();
        if (size < min) {
            throw new IllegalArgumentException(format("List size %s is too small, min %s!", size, min));
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

    public static int sizeMin(int value, int min) {
        if (value < min) {
            throw new IllegalArgumentException(format("Given value %s is too small, min %s!", value, min));
        }
        return value;
    }

    public static long[] checkArrayLength(long[] longs, int length) {
        if (longs.length != length) {
            throw new IllegalArgumentException(
                format("Length of array should be %s but was %s!", length, longs.length));
        }
        return longs;
    }

    public static byte[] checkArrayLength(byte[] bytes, int length) {
        if (bytes.length != length) {
            throw new IllegalArgumentException(
                format("Length of array should be %s but was %s!", length, bytes.length));
        }
        return bytes;
    }

    public static short[] checkArrayLength(short[] shorts, int length) {
        if (shorts.length != length) {
            throw new IllegalArgumentException(
                format("Length of array should be %s but was %s!", length, shorts.length));
        }
        return shorts;
    }

    public static int[] checkArrayLength(int[] ints, int length) {
        if (ints.length != length) {
            throw new IllegalArgumentException(
                format("Length of array should be %s but was %s!", length, ints.length));
        }
        return ints;
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

    public static <T> Collection<T> checkArrayLength(Collection<T> collection, int length) {
        if (collection.size() != length) {
            throw new IllegalArgumentException(
                format("Length of array should be %s but was %s!", length, collection.size()));
        }
        return collection;
    }

    public static int byteSize(int byteValue) {
        return size(byteValue, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    public static int unsignedByteSize(int unsignedByteValue) {
        return size(unsignedByteValue, 0, 255);
    }

    public static short unsignedByteSize(short unsignedByteValue) {
        return size(unsignedByteValue, (short) 0, (short) 255);
    }

    public static int unsignedShortSize(int unsignedByteValue) {
        return size(unsignedByteValue, 0, 65_535);
    }

    public static int[] unsigned(int[] unsignedByteValues) {
        for (int unsignedByteValue : unsignedByteValues) {
            unsigned(unsignedByteValue);
        }
        return unsignedByteValues;
    }

    public static short[] unsigned(short[] unsignedShortValues) {
        for (short unsignedByteValue : unsignedShortValues) {
            unsignedByteSize(unsignedByteValue);
        }
        return unsignedShortValues;
    }

    public static long[] unsigned(long[] unsignedByteValues) {
        for (long unsignedByteValue : unsignedByteValues) {
            unsigned(unsignedByteValue);
        }
        return unsignedByteValues;
    }

    public static long unsigned(final long value) {
        return size(value, 0, 4294967295L);
    }

    /**
     * same as unsignedByteSize
     */
    public static short unsigned(short unsignedByteValue) {
        return size(unsignedByteValue, (short) 0, (short) 255);
    }

    /**
     * same as unsignedShortSize
     */
    public static int unsigned(int unsignedByteValue) {
        return size(unsignedByteValue, 0, 65_535);
    }

    /**
     * same as unsignedIntSize
     */
    public static long unsignedIntSize(final long value) {
        return size(value, 0, 4294967295L);
    }

    public static Long unsignedIntSize(Long value) {
        if (value == null) return null;
        return size(value, 0, 4294967295L);
    }

    public static int equalsTo(int value, int equalTo) {
        if (value != equalTo) {
            throw new IllegalArgumentException(format("Given value %s is not equal to %s!", value, equalTo));
        }
        return value;
    }

    public static long equalsTo(long value, long equalTo) {
        if (value != equalTo) {
            throw new IllegalArgumentException(format("Given value %s is not equal to %s!", value, equalTo));
        }
        return value;
    }

    public static int id(final int id) {
        return positive(id);
    }

    public static int positive(final int positive) {
        return min(positive, 1);
    }

    public static short positive(final short positive) {
        return min(positive, (short) 1);
    }

    public static int positiveOrZero(final int positive) {
        return min(positive, 0);
    }

    public static long positiveOrZero(final long positive) {
        return min(positive, 0);
    }

    public static short positiveOrZero(final short positive) {
        return min(positive, (short) 0);
    }

    public static byte positiveOrZero(final byte positive) {
        return min(positive, (byte) 0);
    }

    public static String notEmpty(String string) {
        requireNonNull(string, "Given parameter was null!");
        if (string.isEmpty()) {
            throw new IllegalArgumentException("Given parameter was empty!");
        }
        return string;
    }

    public static void unionCheck(Object... objects) {
        boolean allNull = true;
        for (Object object : objects) {
            if (object != null) {
                if (!allNull) {
                    throw new IllegalArgumentException("More that one objects in union was not null!");
                }
                allNull = false;
            }
        }
        if (allNull) {
            throw new IllegalArgumentException("All objects in union were null!");
        }
    }

    private Preconditions() {

    }
}
