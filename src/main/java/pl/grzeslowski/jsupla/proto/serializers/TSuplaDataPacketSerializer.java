package pl.grzeslowski.jsupla.proto.serializers;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import static java.lang.System.arraycopy;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.proto.serializers.PrimitiveSerizaliser.putUnsignedByte;
import static pl.grzeslowski.jsupla.proto.serializers.PrimitiveSerizaliser.putUnsignedInt;

public final class TSuplaDataPacketSerializer implements Serializer<TSuplaDataPacket> {
    @Override
    public byte[] serialise(TSuplaDataPacket proto) {
        final byte[] bytes = new byte[proto.size()];

        putUnsignedByte(proto.version, bytes, 0);
        putUnsignedInt(proto.rrId, bytes, INT_SIZE);
        putUnsignedInt(proto.callType, bytes, INT_SIZE * 2);
        arraycopy(proto.data, 0, bytes, INT_SIZE * 3, proto.data.length);

        return bytes;
    }
}
