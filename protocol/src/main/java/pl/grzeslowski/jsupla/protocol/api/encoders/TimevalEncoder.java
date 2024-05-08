package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

public class TimevalEncoder implements Encoder<SuplaTimeval> {
    public static final TimevalEncoder INSTANCE = new TimevalEncoder();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaTimeval proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.INSTANCE.writeInt(proto.seconds, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeInt(proto.milliseconds, bytes, offset);

        return bytes;
    }
}
