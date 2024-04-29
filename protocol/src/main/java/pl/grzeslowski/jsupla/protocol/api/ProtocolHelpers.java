package pl.grzeslowski.jsupla.protocol.api;

import static java.nio.charset.StandardCharsets.UTF_8;

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
}
