package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.structs.TimevalCompat;

public class TimevalCompatDecoder implements ProtoWithSizeDecoder<TimevalCompat> {
    public static final TimevalCompatDecoder INSTANCE = new TimevalCompatDecoder();

    @Override
    public TimevalCompat decode(byte[] bytes, int offset) {
        throw new UnsupportedOperationException("TimevalCompatDecoder.decode(bytes, offset)");
    }
}
