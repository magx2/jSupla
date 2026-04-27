package pl.grzeslowski.jsupla.protocol.api.serialization;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG_SIZE;

import java.util.Arrays;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

public final class ProtocolPacketDeserializer {
    public static final ProtocolPacketDeserializer INSTANCE = new ProtocolPacketDeserializer();
    public static final int HEADER_SIZE = SUPLA_TAG_SIZE + SuplaDataPacket.MIN_SIZE;
    public static final int MIN_FRAME_SIZE = HEADER_SIZE + SUPLA_TAG_SIZE;
    public static final int DATA_SIZE_OFFSET = SUPLA_TAG_SIZE + 1 + 4 + 4;

    private ProtocolPacketDeserializer() {}

    public SuplaDataPacket deserialize(byte[] bytes) {
        if (bytes.length < HEADER_SIZE) {
            throw new ProtocolCodecException(
                    format(
                            "Truncated TSuplaDataPacket header: required=%s, actual=%s",
                            HEADER_SIZE, bytes.length));
        }

        BinaryReader reader = new BinaryReader(bytes);
        validateTag(reader.readBytes(SUPLA_TAG_SIZE, "start tag"), "start");
        short version = reader.readUInt8("version");
        long rrId = reader.readUInt32LE("rr_id");
        long callType = reader.readUInt32LE("call_type");
        long dataSize = reader.readUInt32LE("data_size");
        validateDataSize(dataSize);

        int expectedSize = Math.toIntExact(HEADER_SIZE + dataSize + SUPLA_TAG_SIZE);
        if (bytes.length < expectedSize) {
            throw new ProtocolCodecException(
                    format(
                            "Truncated TSuplaDataPacket payload: data_size=%s,"
                                    + " required_frame_size=%s, actual=%s",
                            dataSize, expectedSize, bytes.length));
        }
        if (bytes.length > expectedSize) {
            throw new ProtocolCodecException(
                    format(
                            "TSuplaDataPacket payload length mismatch: data_size=%s,"
                                    + " expected_frame_size=%s, actual=%s",
                            dataSize, expectedSize, bytes.length));
        }

        byte[] data = reader.readBytes(Math.toIntExact(dataSize), "data");
        validateTag(reader.readBytes(SUPLA_TAG_SIZE, "end tag"), "end");
        reader.requireFullyConsumed("TSuplaDataPacket");

        return new SuplaDataPacket(version, rrId, callType, dataSize, data);
    }

    public long frameSize(byte[] headerBytes) {
        if (headerBytes.length < HEADER_SIZE) {
            throw new ProtocolCodecException(
                    format(
                            "Cannot read frame size from truncated header: required=%s, actual=%s",
                            HEADER_SIZE, headerBytes.length));
        }
        BinaryReader reader = new BinaryReader(headerBytes);
        validateTag(reader.readBytes(SUPLA_TAG_SIZE, "start tag"), "start");
        reader.readUInt8("version");
        reader.readUInt32LE("rr_id");
        reader.readUInt32LE("call_type");
        long dataSize = reader.readUInt32LE("data_size");
        validateDataSize(dataSize);
        return HEADER_SIZE + dataSize + SUPLA_TAG_SIZE;
    }

    private static void validateTag(byte[] tag, String position) {
        if (!Arrays.equals(SUPLA_TAG, tag)) {
            throw new ProtocolCodecException(
                    format(
                            "Invalid SUPLA %s tag: expected=%s, actual=%s",
                            position, Arrays.toString(SUPLA_TAG), Arrays.toString(tag)));
        }
    }

    private static void validateDataSize(long dataSize) {
        if (dataSize > SUPLA_MAX_DATA_SIZE) {
            throw new ProtocolCodecException(
                    "TSuplaDataPacket data_size "
                            + dataSize
                            + " exceeds SUPLA_MAX_DATA_SIZE "
                            + SUPLA_MAX_DATA_SIZE);
        }
    }
}
