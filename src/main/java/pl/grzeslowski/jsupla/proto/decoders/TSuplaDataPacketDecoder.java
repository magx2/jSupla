package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.proto.decoders.PrimitiveParser.parseUnsignedInt;

public final class TSuplaDataPacketDecoder implements Decoder<TSuplaDataPacket> {
    @Override
    public TSuplaDataPacket decode(byte[] bytes, int offset) {
        final byte version = bytes[offset + 5];
        int rrId = parseUnsignedInt(bytes, offset + 6);
        int callType = parseUnsignedInt(bytes, offset + 10);
        int dataSize = parseUnsignedInt(bytes, offset + 14);
        byte[] data = copyOfRange(bytes, offset + 18, offset + bytes.length);
        return new TSuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
