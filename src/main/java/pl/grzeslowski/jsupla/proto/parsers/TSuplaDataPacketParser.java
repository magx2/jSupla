package pl.grzeslowski.jsupla.proto.parsers;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.proto.parsers.PrimitiveParser.parseUnsignedInt;

public final class TSuplaDataPacketParser implements Parser<TSuplaDataPacket> {
    @Override
    public TSuplaDataPacket parse(byte[] bytes) {
        final byte version = bytes[5];
        int rrId = parseUnsignedInt(bytes, 6);
        int callType = parseUnsignedInt(bytes, 10);
        int dataSize = parseUnsignedInt(bytes, 14);
        byte[] data = copyOfRange(bytes, 18, bytes.length);
        return new TSuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
