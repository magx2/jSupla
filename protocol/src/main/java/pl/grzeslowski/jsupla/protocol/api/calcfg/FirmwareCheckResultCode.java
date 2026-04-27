package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_FIRMWARE_CHECK_RESULT_ERROR;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_NOT_AVAILABLE;

import java.util.Arrays;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

public enum FirmwareCheckResultCode {
    UPDATE_NOT_AVAILABLE(SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_NOT_AVAILABLE),
    UPDATE_AVAILABLE(SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE),
    ERROR(SUPLA_FIRMWARE_CHECK_RESULT_ERROR);

    private final int value;

    FirmwareCheckResultCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public short unsignedByteValue() {
        return (short) value;
    }

    public static FirmwareCheckResultCode fromValue(int value) {
        return Arrays.stream(values())
                .filter(result -> result.value == value)
                .findFirst()
                .orElseThrow(
                        () ->
                                new ProtocolCodecException(
                                        "Unsupported SUPLA_FIRMWARE_CHECK_RESULT value " + value));
    }
}
