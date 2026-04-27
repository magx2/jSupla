package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_DATA_MAXSIZE;

import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryReader;
import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryWriter;
import pl.grzeslowski.jsupla.protocol.api.serialization.PayloadCodec;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

public final class DeviceCalCfgResultCodec implements PayloadCodec<TdsDeviceCalCfgResult> {
    public static final DeviceCalCfgResultCodec INSTANCE = new DeviceCalCfgResultCodec();

    private DeviceCalCfgResultCodec() {}

    @Override
    public byte[] encode(TdsDeviceCalCfgResult payload) {
        BinaryWriter writer = new BinaryWriter(payload.protoSize());
        writer.writeInt32LE(payload.receiverId(), "ReceiverID");
        writer.writeInt32LE(payload.channelNumber(), "ChannelNumber");
        writer.writeInt32LE(payload.command(), "Command");
        writer.writeInt32LE(payload.result(), "Result");
        writer.writeInt32LE(payload.dataType(), "DataType");
        writer.writeUInt32LE(payload.dataSize(), "DataSize");
        writer.writeBytes(payload.data(), "Data");
        return writer.toByteArray();
    }

    @Override
    public TdsDeviceCalCfgResult decode(byte[] bytes) {
        BinaryReader reader = new BinaryReader(bytes);
        int receiverId = reader.readInt32LE("ReceiverID");
        int channelNumber = reader.readInt32LE("ChannelNumber");
        int command = reader.readInt32LE("Command");
        int result = reader.readInt32LE("Result");
        int dataType = reader.readInt32LE("DataType");
        long dataSize = reader.readUInt32LE("DataSize");
        validateDataSize(dataSize, reader.remaining());
        byte[] data = reader.readBytes(Math.toIntExact(dataSize), "Data");
        reader.requireFullyConsumed("TDS_DeviceCalCfgResult");
        return new TdsDeviceCalCfgResult(
                receiverId, channelNumber, command, result, dataType, dataSize, data);
    }

    private static void validateDataSize(long dataSize, int remaining) {
        if (dataSize > SUPLA_CALCFG_DATA_MAXSIZE) {
            throw new ProtocolCodecException(
                    "TDS_DeviceCalCfgResult DataSize "
                            + dataSize
                            + " exceeds SUPLA_CALCFG_DATA_MAXSIZE "
                            + SUPLA_CALCFG_DATA_MAXSIZE);
        }
        if (dataSize > remaining) {
            throw new ProtocolCodecException(
                    "TDS_DeviceCalCfgResult DataSize "
                            + dataSize
                            + " exceeds remaining payload bytes "
                            + remaining);
        }
    }
}
