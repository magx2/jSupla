package pl.grzeslowski.jsupla.proto.parsers;

import pl.grzeslowski.jsupla.proto.TSuplaDataPacket;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.proto.parsers.PrimitiveParser.parseInt;

public class TSuplaDataPacketParser implements Parser<TSuplaDataPacket> {
    @Override
    public TSuplaDataPacket parse(byte[] bytes) {
        final byte version = bytes[5];
        int rrId = parseInt(bytes, 6);
        int callType = parseInt(bytes, 10);
        int dataSize = parseInt(bytes, 14);
        byte[] data = copyOfRange(bytes, 18, bytes.length);
        return new TSuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
