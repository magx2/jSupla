package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface Encoder<T extends ProtoWithSize> {
    SuplaDataPacket encode(T proto);
}
