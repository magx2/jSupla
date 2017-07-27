package pl.grzeslowski.jsupla.proto.encoders;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.proto.structs.sd.ServerDevice;

public interface Encoder<SD extends ServerDevice> {
    TSuplaDataPacket encode(SD proto);
}
