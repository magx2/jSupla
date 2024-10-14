package pl.grzeslowski.jsupla.protocol.api;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface JavaConsts {
    public static final int INT_SIZE = 4;
    public static final int LONG_SIZE = 8;
    public static final int DOUBLE_SIZE = 8;
    public static final int BYTE_SIZE = 1;
    public static final int SHORT_SIZE = 2;
    public static final int CHAR_SIZE = 1;

    public static final int UNSIGNED_BYTE_MAX = 255;
    public static final long UNSIGNED_INT_MAX = 4294967295L;

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
            size += proto.size();
        }
        return size;
    }
}
