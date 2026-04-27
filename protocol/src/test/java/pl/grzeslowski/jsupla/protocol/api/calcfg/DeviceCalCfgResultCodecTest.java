package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.CalCfgCommand.SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.CalCfgResult.SUPLA_CALCFG_RESULT_TRUE;
import static pl.grzeslowski.jsupla.protocol.api.FirmwareCheckResultCode.SUPLA_FIRMWARE_CHECK_RESULT_ERROR;
import static pl.grzeslowski.jsupla.protocol.api.FirmwareCheckResultCode.SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_RESULT_TRUE;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

class DeviceCalCfgResultCodecTest {
    private final DeviceCalCfgResultCodec codec = DeviceCalCfgResultCodec.INSTANCE;
    private final FirmwareCheckResultCodec firmwareCheckResultCodec =
            FirmwareCheckResultCodec.INSTANCE;

    @Test
    void deviceCalCfgResultShouldRoundTrip() {
        byte[] firmwareCheckPayload =
                firmwareCheckResultCodec.encode(
                        CalCfgFirmwareCheckResult.of(
                                SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE,
                                "4.8.1",
                                "/firmware/changelog"));
        TdsDeviceCalCfgResult result =
                new TdsDeviceCalCfgResult(
                        0x01020304,
                        TsdDeviceCalCfgRequest.DEVICE_CHANNEL_NUMBER,
                        SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE.getValue(),
                        SUPLA_CALCFG_RESULT_TRUE.getValue(),
                        0x11121314,
                        firmwareCheckPayload.length,
                        firmwareCheckPayload);

        TdsDeviceCalCfgResult decoded = codec.decode(codec.encode(result));

        assertThat(decoded.receiverId()).isEqualTo(result.receiverId());
        assertThat(decoded.channelNumber()).isEqualTo(result.channelNumber());
        assertThat(decoded.command()).isEqualTo(result.command());
        assertThat(decoded.result()).isEqualTo(result.result());
        assertThat(decoded.dataType()).isEqualTo(result.dataType());
        assertThat(decoded.dataSize()).isEqualTo(result.dataSize());
        assertThat(decoded.data()).containsExactly(result.data());
    }

    @Test
    void deviceCalCfgResultShouldDecodeEmbeddedFirmwareCheckResult() {
        byte[] firmwareCheckPayload =
                firmwareCheckResultCodec.encode(
                        CalCfgFirmwareCheckResult.of(
                                SUPLA_FIRMWARE_CHECK_RESULT_ERROR, "0.0.0", "/errors"));
        TdsDeviceCalCfgResult result =
                new TdsDeviceCalCfgResult(
                        7,
                        -1,
                        SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE.getValue(),
                        SUPLA_CALCFG_RESULT_TRUE.getValue(),
                        0,
                        firmwareCheckPayload.length,
                        firmwareCheckPayload);

        TdsDeviceCalCfgResult decoded = codec.decode(codec.encode(result));

        assertThat(decoded.firmwareCheckResult()).isPresent();
        CalCfgFirmwareCheckResult firmwareCheckResult = decoded.firmwareCheckResult().get();
        assertThat(firmwareCheckResult.resultCode()).isEqualTo(SUPLA_FIRMWARE_CHECK_RESULT_ERROR);
        assertThat(firmwareCheckResult.softVerString()).isEqualTo("0.0.0");
        assertThat(firmwareCheckResult.changelogUrlString()).isEqualTo("/errors");
    }

    @Test
    void malformedDeviceCalCfgResultShouldRejectDataSizeLargerThanRemainingPayload() {
        byte[] firmwareCheckPayload =
                firmwareCheckResultCodec.encode(
                        CalCfgFirmwareCheckResult.of(
                                SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE, "1", "/"));
        TdsDeviceCalCfgResult result =
                new TdsDeviceCalCfgResult(
                        1,
                        -1,
                        SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE.getValue(),
                        SUPLA_CALCFG_RESULT_TRUE.getValue(),
                        0,
                        firmwareCheckPayload.length,
                        firmwareCheckPayload);
        byte[] encoded = codec.encode(result);
        byte[] truncated = Arrays.copyOf(encoded, encoded.length - 1);

        assertThatThrownBy(() -> codec.decode(truncated))
                .isInstanceOf(ProtocolCodecException.class)
                .hasMessageContaining("exceeds remaining payload bytes");
    }
}
