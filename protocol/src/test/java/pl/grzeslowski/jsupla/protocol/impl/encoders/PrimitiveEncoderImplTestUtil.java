package pl.grzeslowski.jsupla.protocol.impl.encoders;

import java.util.Arrays;

public class PrimitiveEncoderImplTestUtil {
    static byte[] removeOffset(byte[] bytes, int offset) {
        return Arrays.copyOfRange(bytes, offset, bytes.length);
    }
}
