package pl.grzeslowski.jsupla.protocol.impl.decoders.dcs;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaPingServerDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.impl.decoders.TimevalDecoderImpl;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerDecoderImpl implements SuplaPingServerDecoder {
    public static final SuplaPingServerDecoderImpl INSTANCE = new SuplaPingServerDecoderImpl(
        TimevalDecoderImpl.INSTANCE);
    private final TimevalDecoder timevalDecoder;

    public SuplaPingServerDecoderImpl(final TimevalDecoder timevalDecoder) {
        this.timevalDecoder = requireNonNull(timevalDecoder);
    }

    @Override
    public SuplaPingServer decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaPingServer.SIZE);
        final SuplaTimeval timeval = timevalDecoder.decode(bytes, offset);
        return new SuplaPingServer(timeval);
    }
}
