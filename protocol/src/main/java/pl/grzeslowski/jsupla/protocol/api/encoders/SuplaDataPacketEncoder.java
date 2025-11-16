package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaDataPacketEncoder implements ProtoWithSizeEncoder<SuplaDataPacket> {
    public static final SuplaDataPacketEncoder INSTANCE = new SuplaDataPacketEncoder();

    @Override
    public byte[] encode(SuplaDataPacket proto) {
        final byte[] bytes = new byte[proto.protoSize()];
        int offset = 0;

        offset += PrimitiveEncoder.INSTANCE.writeUnsignedByte(proto.version, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedInt(proto.rrId, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedInt(proto.callId, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedInt(proto.dataSize, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeByteArray(proto.data, bytes, offset);

        return bytes;
    }
}
