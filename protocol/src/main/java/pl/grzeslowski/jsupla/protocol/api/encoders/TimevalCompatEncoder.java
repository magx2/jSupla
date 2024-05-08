package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.TimevalCompat;

public class TimevalCompatEncoder implements Encoder<TimevalCompat> {
    public static final TimevalCompatEncoder INSTANCE = new TimevalCompatEncoder();

    @Override
    public byte[] encode(TimevalCompat proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.INSTANCE.writeInt(proto.seconds, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeInt(proto.milliseconds, bytes, offset);

        return bytes;
    }
}
