package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface Encoder<T extends Proto> {
    SuplaDataPacket encode(T proto);
}
