package pl.grzeslowski.jsupla.protocol.impl.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.dcs.SuplaPingServerEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerEncoderImpl implements SuplaPingServerEncoder {
    private final TimevalEncoder timevalEncoder;

    public SuplaPingServerEncoderImpl(final TimevalEncoder timevalEncoder) {
        this.timevalEncoder = requireNonNull(timevalEncoder);
    }

    @Override
    public byte[] encode(final SuplaPingServer proto) {
        return timevalEncoder.encode(proto.timeval);
    }
}
