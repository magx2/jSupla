package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface ToSuplaDataPacketEncoder<T extends ProtoToSend> extends Encoder<T> {
    default SuplaDataPacket encode(T proto, short version, long rrId) {
        final byte[] data = encode(proto);
        return new SuplaDataPacket(version, rrId, proto.callType().getValue(), data.length, data);
    }
}
