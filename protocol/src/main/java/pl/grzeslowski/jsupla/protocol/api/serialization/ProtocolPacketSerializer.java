package pl.grzeslowski.jsupla.protocol.api.serialization;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG_SIZE;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

public final class ProtocolPacketSerializer {
    public static final ProtocolPacketSerializer INSTANCE = new ProtocolPacketSerializer();

    private ProtocolPacketSerializer() {}

    public byte[] serialize(SuplaDataPacket packet) {
        validatePacket(packet);
        int dataSize = Math.toIntExact(packet.dataSize());
        int size = SUPLA_TAG_SIZE + SuplaDataPacket.MIN_SIZE + dataSize + SUPLA_TAG_SIZE;
        BinaryWriter writer = new BinaryWriter(size);

        writer.writeFixedBytes(SUPLA_TAG, SUPLA_TAG_SIZE, "start tag");
        writer.writeUInt8(packet.version(), "version");
        writer.writeUInt32LE(packet.rrId(), "rr_id");
        writer.writeUInt32LE(packet.callId(), "call_type");
        writer.writeUInt32LE(packet.dataSize(), "data_size");
        writer.writeBytes(packet.data(), "data");
        writer.writeFixedBytes(SUPLA_TAG, SUPLA_TAG_SIZE, "end tag");

        return writer.toByteArray();
    }

    private static void validatePacket(SuplaDataPacket packet) {
        if (packet.dataSize() > SUPLA_MAX_DATA_SIZE) {
            throw new ProtocolCodecException(
                    "TSuplaDataPacket data_size "
                            + packet.dataSize()
                            + " exceeds SUPLA_MAX_DATA_SIZE "
                            + SUPLA_MAX_DATA_SIZE);
        }
        if (packet.data().length != packet.dataSize()) {
            throw new ProtocolCodecException(
                    "TSuplaDataPacket data length "
                            + packet.data().length
                            + " does not match data_size "
                            + packet.dataSize());
        }
        if (SUPLA_TAG.length != SUPLA_TAG_SIZE) {
            throw new ProtocolCodecException("Configured SUPLA tag has invalid size");
        }
    }
}
