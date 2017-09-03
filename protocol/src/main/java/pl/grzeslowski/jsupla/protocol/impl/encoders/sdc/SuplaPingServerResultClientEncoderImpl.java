package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaPingServerResultClientEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerResultClientEncoderImpl implements SuplaPingServerResultClientEncoder {
    private final TimevalEncoder timevalEncoder;

    public SuplaPingServerResultClientEncoderImpl(final TimevalEncoder timevalEncoder) {
        this.timevalEncoder = requireNonNull(timevalEncoder);
    }

    @Override
    public byte[] encode(final SuplaPingServerResultClient proto) {
        return timevalEncoder.encode(proto.timeval);
    }
}
