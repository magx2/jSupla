package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;

public interface Encoder<T extends Proto> {
    TSuplaDataPacket encode(T proto);
}
