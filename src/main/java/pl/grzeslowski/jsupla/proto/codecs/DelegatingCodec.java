package pl.grzeslowski.jsupla.proto.codecs;

import pl.grzeslowski.jsupla.proto.decoders.Decoder;
import pl.grzeslowski.jsupla.proto.encoders.Encoder;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.proto.structs.sd.ServerDevice;

import static java.util.Objects.requireNonNull;

public abstract class DelegatingCodec<SD extends ServerDevice, DS extends DeviceServer> implements Codec<SD, DS> {
    private final Encoder<SD> encoder;
    private final Decoder<DS> decoder;

    public DelegatingCodec(Encoder<SD> encoder, Decoder<DS> decoder) {
        this.encoder = requireNonNull(encoder);
        this.decoder = requireNonNull(decoder);
    }

    @Override
    public DS decode(TSuplaDataPacket dataPacket) {
        return decoder.decode(dataPacket);
    }

    @Override
    public TSuplaDataPacket encode(SD proto) {
        return encoder.encode(proto);
    }
}
