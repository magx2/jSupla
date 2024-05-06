package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoToSend;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

public interface ToSuplaDataPacketEncoder<T extends ProtoToSend> extends Encoder<T> {
    default SuplaDataPacket encode(T proto, short version, long rrId) {
        final byte[] data = encode(proto);
        return new SuplaDataPacket(SUPLA_TAG, version, rrId, proto.callType().getValue(), data.length, data);
    }
}
