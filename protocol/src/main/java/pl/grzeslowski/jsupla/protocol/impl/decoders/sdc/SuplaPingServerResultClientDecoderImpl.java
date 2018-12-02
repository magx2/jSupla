package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaPingServerResultClientDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.TimevalDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaEventDecoderImpl;

import static java.util.Objects.requireNonNull;

public class SuplaPingServerResultClientDecoderImpl implements SuplaPingServerResultClientDecoder {
    public static final SuplaPingServerResultClientDecoderImpl INSTANCE = new SuplaPingServerResultClientDecoderImpl(
            TimevalDecoderImpl.INSTANCE);
    private final TimevalDecoder timevalDecoder;

    public SuplaPingServerResultClientDecoderImpl(final TimevalDecoder timevalDecoder) {
        this.timevalDecoder = requireNonNull(timevalDecoder);
    }

    @Override
    public SuplaPingServerResultClient decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaPingServerResultClient.SIZE);
        final SuplaTimeval timeval = timevalDecoder.decode(bytes, offset);
        return new SuplaPingServerResultClient(timeval);
    }
}
