package pl.grzeslowski.jsupla.protocol.impl.decoders.dcs;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaPingServerDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerDecoderImpl implements SuplaPingServerDecoder {
    private final TimevalDecoder timevalDecoder;

    public SuplaPingServerDecoderImpl(final TimevalDecoder timevalDecoder) {
        this.timevalDecoder = requireNonNull(timevalDecoder);
    }

    @Override
    public SuplaPingServer decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaPingServer.SIZE);
        final Timeval timeval = timevalDecoder.decode(bytes, offset);
        return new SuplaPingServer(timeval);
    }
}
