package pl.grzeslowski.jsupla.proto.encoders;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

public final class TSuplaDataPacketEncoder implements Encoder<TSuplaDataPacket> {

    @Override
    public void encode(TSuplaDataPacket proto, byte[] bytes, int offset) {

        // TODO
//        putUnsignedByte(proto.version, bytes, 0);
//        putUnsignedInt(proto.rrId, bytes, BYTE_SIZE);
//        putUnsignedInt(proto.callType, bytes, BYTE_SIZE + INT_SIZE );
//        putUnsignedInt(proto.dataSize, bytes, BYTE_SIZE + INT_SIZE *2);
//        arraycopy(proto.data, 0, bytes, BYTE_SIZE + INT_SIZE * 3, proto.data.length);

    }
}