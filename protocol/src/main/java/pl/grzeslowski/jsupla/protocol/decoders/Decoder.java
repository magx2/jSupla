package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface Decoder<T extends Proto> {
    T decode(byte[] bytes, int offset);

    default T decode(SuplaDataPacket dataPacket) {
        return decode(dataPacket.data, 0);
    }

    ;
}
