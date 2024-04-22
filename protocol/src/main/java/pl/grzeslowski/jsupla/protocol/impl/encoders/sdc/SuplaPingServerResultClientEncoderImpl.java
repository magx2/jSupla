package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaPingServerResultClientEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.impl.encoders.TimevalEncoderImpl;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerResultClientEncoderImpl implements SuplaPingServerResultClientEncoder {
    public static final SuplaPingServerResultClientEncoderImpl INSTANCE =
        new SuplaPingServerResultClientEncoderImpl(TimevalEncoderImpl.INSTANCE);
    private final TimevalEncoder timevalEncoder;

    SuplaPingServerResultClientEncoderImpl(final TimevalEncoder timevalEncoder) {
        this.timevalEncoder = requireNonNull(timevalEncoder);
    }

    @Override
    public byte[] encode(final SuplaPingServerResultClient proto) {
        return timevalEncoder.encode(proto.timeval);
    }
}
