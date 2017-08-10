package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

public class SuplaDataPacketEncoder implements Encoder<SuplaDataPacket> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaDataPacket proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeUnsignedByte(proto.version, data, offset);
        offset += PrimitiveEncoder.writeUnsignedInteger(proto.rrId, data, offset);
        offset += PrimitiveEncoder.writeUnsignedInteger(proto.callType, data, offset);
        offset += PrimitiveEncoder.writeUnsignedInteger(proto.dataSize, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.data, data, offset);

        return data;
    }
}
