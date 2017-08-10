package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface Decoder<T extends PackableProto> {
    T decode(byte[] bytes, int offset);

    default T decode(SuplaDataPacket dataPacket) {
        return decode(dataPacket.data, 0);
    }

    ;
}
