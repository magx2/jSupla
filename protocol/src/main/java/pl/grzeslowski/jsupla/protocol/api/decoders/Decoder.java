package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface Decoder<T extends ProtoWithSize> {
    T decode(byte[] bytes, int offset);

    default T decode(SuplaDataPacket dataPacket) {
        return decode(dataPacket.data, 0);
    }
}
