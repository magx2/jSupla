package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public interface SuplaDataPacketEncoder<T extends ProtoWithCallType & ProtoWithSize> extends Encoder<T> {
    default SuplaDataPacket encode(T proto, short version, long rrId) {
        final byte[] data = encode(proto);
        return new SuplaDataPacket(version, rrId, proto.callType().getValue(), data.length, data);
    }
}
