package pl.grzeslowski.jsupla.protocol.api.encoders;

import java.util.Arrays;

public class PrimitiveEncoderTestUtil {
    static byte[] removeOffset(byte[] bytes, int offset) {
        return Arrays.copyOfRange(bytes, offset, bytes.length);
    }
}
