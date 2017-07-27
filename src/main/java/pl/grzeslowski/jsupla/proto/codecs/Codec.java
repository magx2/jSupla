package pl.grzeslowski.jsupla.proto.codecs;

import pl.grzeslowski.jsupla.consts.CallTypes;
import pl.grzeslowski.jsupla.proto.decoders.Decoder;
import pl.grzeslowski.jsupla.proto.encoders.Encoder;
import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.proto.structs.sd.ServerDevice;

public interface Codec<SD extends ServerDevice, DS extends DeviceServer> extends Encoder<SD>, Decoder<DS> {
    CallTypes callTypeForCodec();
}
