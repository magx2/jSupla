package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_DEVICE_CALCFG_REQUEST;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_DATA_MAXSIZE;

import java.util.Arrays;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;

public record TsdDeviceCalCfgRequest(
        int senderId,
        int channelNumber,
        int command,
        byte superUserAuthorized,
        int dataType,
        long dataSize,
        byte[] data)
        implements ServerDevice {
    public static final int DEVICE_CHANNEL_NUMBER = -1;
    public static final int HEADER_SIZE = Integer.BYTES * 5 + Byte.BYTES;

    public TsdDeviceCalCfgRequest {
        CalCfgCommand.fromValue(command);
        validateSuperUserAuthorized(superUserAuthorized);
        validateData(dataSize, data);
        data = data.clone();
    }

    public static TsdDeviceCalCfgRequest deviceOtaCommand(
            int senderId, CalCfgCommand command, boolean superUserAuthorized) {
        return new TsdDeviceCalCfgRequest(
                senderId,
                DEVICE_CHANNEL_NUMBER,
                command.value(),
                (byte) (superUserAuthorized ? 1 : 0),
                0,
                0,
                new byte[0]);
    }

    @Override
    public ServerDeviceCallType callType() {
        return SUPLA_SD_CALL_DEVICE_CALCFG_REQUEST;
    }

    @Override
    public int protoSize() {
        return HEADER_SIZE + Math.toIntExact(dataSize);
    }

    public CalCfgCommand commandCode() {
        return CalCfgCommand.fromValue(command);
    }

    @Override
    public byte[] data() {
        return data.clone();
    }

    @Override
    public String toString() {
        return "TsdDeviceCalCfgRequest["
                + "senderId="
                + senderId
                + ", channelNumber="
                + channelNumber
                + ", command="
                + command
                + ", superUserAuthorized="
                + superUserAuthorized
                + ", dataType="
                + dataType
                + ", dataSize="
                + dataSize
                + ", data="
                + Arrays.toString(data)
                + "]";
    }

    private static void validateSuperUserAuthorized(byte superUserAuthorized) {
        if (superUserAuthorized != 0 && superUserAuthorized != 1) {
            throw new ProtocolCodecException(
                    "SuperUserAuthorized must be 0 or 1, got " + superUserAuthorized);
        }
    }

    private static void validateData(long dataSize, byte[] data) {
        if (dataSize < 0 || dataSize > SUPLA_CALCFG_DATA_MAXSIZE) {
            throw new ProtocolCodecException(
                    "DataSize " + dataSize + " is outside 0.." + SUPLA_CALCFG_DATA_MAXSIZE);
        }
        if (data.length != dataSize) {
            throw new ProtocolCodecException(
                    "Data length " + data.length + " does not match DataSize " + dataSize);
        }
    }
}
