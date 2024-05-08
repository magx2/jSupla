package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.TimevalCompat;

public class TimevalCompatEncoder implements Encoder<TimevalCompat> {
    public static final TimevalCompatEncoder INSTANCE = new TimevalCompatEncoder();

    @Override
    public byte[] encode(TimevalCompat proto) {
        throw new UnsupportedOperationException("TimevalCompatEncoder.encode(proto)");
    }
}
