package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;

public interface Encoder<SD extends ServerDevice> {
    TSuplaDataPacket encode(SD proto);
}
