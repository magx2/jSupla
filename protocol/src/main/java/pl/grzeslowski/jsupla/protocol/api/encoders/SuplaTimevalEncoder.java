package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

public class SuplaTimevalEncoder implements Encoder<SuplaTimeval> {
    public static final SuplaTimevalEncoder INSTANCE = new SuplaTimevalEncoder();

    @Override
    public byte[] encode(SuplaTimeval proto) {
        throw new UnsupportedOperationException("SuplaTimevalEncoder.encode(proto)");
    }
}
