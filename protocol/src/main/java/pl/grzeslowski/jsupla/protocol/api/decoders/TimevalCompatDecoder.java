package pl.grzeslowski.jsupla.protocol.api.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.structs.TimevalCompat;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

public class TimevalCompatDecoder implements ProtoWithSizeDecoder<TimevalCompat> {
    public static final TimevalCompatDecoder INSTANCE = new TimevalCompatDecoder();

    @Override
    public TimevalCompat decode(byte[] bytes, int offset) {
        val seconds = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;
        final int milliseconds = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);

        return new TimevalCompat(seconds, milliseconds);
    }
}
