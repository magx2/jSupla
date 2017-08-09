package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface Decoder<T extends Proto> {
    T decode(SuplaDataPacket dataPacket);
}
