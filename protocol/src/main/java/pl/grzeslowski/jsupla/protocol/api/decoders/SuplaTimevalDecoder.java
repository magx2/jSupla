package pl.grzeslowski.jsupla.protocol.api.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.LONG_SIZE;

@javax.annotation.Generated(value = "Struct original name: _supla_timeval", date = "2024-11-08T14:34:34.589+01:00[Europe/Belgrade]")
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaTimevalDecoder implements ProtoWithSizeDecoder<SuplaTimeval> {
    public static final SuplaTimevalDecoder INSTANCE = new SuplaTimevalDecoder();

    @Override
    public SuplaTimeval decode(byte[] bytes, int offset) {
        if (bytes.length < 2 * LONG_SIZE) {
            // parse simple SuplaTimeval that has 2 integers
            val tvSec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val tvUsec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            return new SuplaTimeval(tvSec, tvUsec);
        }
        val tvSec = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
        offset += LONG_SIZE;

        val tvUsec = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
        offset += LONG_SIZE;

        return new SuplaTimeval(tvSec, tvUsec);
    }
}
