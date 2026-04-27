package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_DATA_MAXSIZE;

import java.util.Arrays;
import java.util.Optional;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;

public record TdsDeviceCalCfgResult(
        int receiverId,
        int channelNumber,
        int command,
        int result,
        int dataType,
        long dataSize,
        byte[] data)
        implements DeviceServer {
    public static final int HEADER_SIZE = Integer.BYTES * 6;

    public TdsDeviceCalCfgResult {
        CalCfgCommand.fromValue(command);
        CalCfgResult.fromValue(result);
        validateData(dataSize, data);
        data = data.clone();
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;
    }

    @Override
    public int protoSize() {
        return HEADER_SIZE + Math.toIntExact(dataSize);
    }

    public CalCfgCommand commandCode() {
        return CalCfgCommand.fromValue(command);
    }

    public CalCfgResult resultCode() {
        return CalCfgResult.fromValue(result);
    }

    public Optional<CalCfgFirmwareCheckResult> firmwareCheckResult() {
        if (commandCode() != CalCfgCommand.CHECK_FIRMWARE_UPDATE) {
            return Optional.empty();
        }
        return Optional.of(FirmwareCheckResultCodec.INSTANCE.decode(data));
    }

    @Override
    public byte[] data() {
        return data.clone();
    }

    @Override
    public String toString() {
        return "TdsDeviceCalCfgResult["
                + "receiverId="
                + receiverId
                + ", channelNumber="
                + channelNumber
                + ", command="
                + command
                + ", result="
                + result
                + ", dataType="
                + dataType
                + ", dataSize="
                + dataSize
                + ", data="
                + Arrays.toString(data)
                + "]";
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
