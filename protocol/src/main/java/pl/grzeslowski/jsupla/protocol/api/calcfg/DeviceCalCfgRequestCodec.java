package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_DATA_MAXSIZE;

import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryReader;
import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryWriter;
import pl.grzeslowski.jsupla.protocol.api.serialization.PayloadCodec;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

public final class DeviceCalCfgRequestCodec implements PayloadCodec<TsdDeviceCalCfgRequest> {
    public static final DeviceCalCfgRequestCodec INSTANCE = new DeviceCalCfgRequestCodec();

    private DeviceCalCfgRequestCodec() {}

    @Override
    public byte[] encode(TsdDeviceCalCfgRequest payload) {
        BinaryWriter writer = new BinaryWriter(payload.protoSize());
        writer.writeInt32LE(payload.senderId(), "SenderID");
        writer.writeInt32LE(payload.channelNumber(), "ChannelNumber");
        writer.writeInt32LE(payload.command(), "Command");
        writer.writeInt8(payload.superUserAuthorized(), "SuperUserAuthorized");
        writer.writeInt32LE(payload.dataType(), "DataType");
        writer.writeUInt32LE(payload.dataSize(), "DataSize");
        writer.writeBytes(payload.data(), "Data");
        return writer.toByteArray();
    }

    @Override
    public TsdDeviceCalCfgRequest decode(byte[] bytes) {
        BinaryReader reader = new BinaryReader(bytes);
        int senderId = reader.readInt32LE("SenderID");
        int channelNumber = reader.readInt32LE("ChannelNumber");
        int command = reader.readInt32LE("Command");
        byte superUserAuthorized = reader.readInt8("SuperUserAuthorized");
        int dataType = reader.readInt32LE("DataType");
        long dataSize = reader.readUInt32LE("DataSize");
        validateDataSize(dataSize, reader.remaining());
        byte[] data = reader.readBytes(Math.toIntExact(dataSize), "Data");
        reader.requireFullyConsumed("TSD_DeviceCalCfgRequest");
        return new TsdDeviceCalCfgRequest(
                senderId, channelNumber, command, superUserAuthorized, dataType, dataSize, data);
    }

    private static void validateDataSize(long dataSize, int remaining) {
        if (dataSize > SUPLA_CALCFG_DATA_MAXSIZE) {
            throw new ProtocolCodecException(
                    "TSD_DeviceCalCfgRequest DataSize "
                            + dataSize
                            + " exceeds SUPLA_CALCFG_DATA_MAXSIZE "
                            + SUPLA_CALCFG_DATA_MAXSIZE);
        }
        if (dataSize > remaining) {
            throw new ProtocolCodecException(
                    "TSD_DeviceCalCfgRequest DataSize "
                            + dataSize
                            + " exceeds remaining payload bytes "
                            + remaining);
        }
    }
}
