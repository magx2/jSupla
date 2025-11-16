package pl.grzeslowski.jsupla.protocol.api.decoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaDataPacketDecoder implements ProtoWithSizeDecoder<SuplaDataPacket> {
    public static final SuplaDataPacketDecoder INSTANCE = new SuplaDataPacketDecoder();

    @Override
    public SuplaDataPacket decode(byte[] bytes, int offset) {
        val version = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val rrId = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        val callId = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        val dataSize = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        val data =
                PrimitiveDecoder.INSTANCE.copyOfRangeByte(bytes, offset, offset + (int) dataSize);
        offset += dataSize * BYTE_SIZE;

        return new SuplaDataPacket(version, rrId, callId, dataSize, data);
    }
}
