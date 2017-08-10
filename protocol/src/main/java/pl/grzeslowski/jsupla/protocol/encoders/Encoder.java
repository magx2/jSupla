package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface Encoder<T extends PackableProto> {
    SuplaDataPacket encode(T proto);
}
