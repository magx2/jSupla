package pl.grzeslowski.jsupla.protocol.impl.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.dcs.SuplaPingServerEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.impl.encoders.TimevalEncoderImpl;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerEncoderImpl implements SuplaPingServerEncoder {
    public static final SuplaPingServerEncoderImpl INSTANCE =
        new SuplaPingServerEncoderImpl(TimevalEncoderImpl.INSTANCE);
    private final TimevalEncoder timevalEncoder;

    SuplaPingServerEncoderImpl(final TimevalEncoder timevalEncoder) {
        this.timevalEncoder = requireNonNull(timevalEncoder);
    }

    @Override
    public byte[] encode(final SuplaPingServer proto) {
        return timevalEncoder.encode(proto.timeval);
    }
}
