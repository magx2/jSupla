package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;

public interface Decoder<T extends Proto> {
    T decode(TSuplaDataPacket dataPacket);
}
