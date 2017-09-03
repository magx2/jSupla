package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaPingServerResultClientDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerResultClientDecoderImpl implements SuplaPingServerResultClientDecoder {
    private final TimevalDecoder timevalDecoder;

    public SuplaPingServerResultClientDecoderImpl(final TimevalDecoder timevalDecoder) {
        this.timevalDecoder = requireNonNull(timevalDecoder);
    }

    @Override
    public SuplaPingServerResultClient decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaPingServerResultClient.SIZE);
        final Timeval timeval = timevalDecoder.decode(bytes, offset);
        return new SuplaPingServerResultClient(timeval);
    }
}
