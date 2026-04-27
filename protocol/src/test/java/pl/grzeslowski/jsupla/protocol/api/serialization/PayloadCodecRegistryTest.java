package pl.grzeslowski.jsupla.protocol.api.serialization;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_DEVICE_CALCFG_REQUEST;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CALCFG_RESULT_TRUE;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.calcfg.CalCfgFirmwareCheckResult;
import pl.grzeslowski.jsupla.protocol.api.calcfg.DeviceCalCfgResultCodec;
import pl.grzeslowski.jsupla.protocol.api.calcfg.FirmwareCheckResultCode;
import pl.grzeslowski.jsupla.protocol.api.calcfg.FirmwareCheckResultCodec;
import pl.grzeslowski.jsupla.protocol.api.calcfg.TdsDeviceCalCfgResult;
import pl.grzeslowski.jsupla.protocol.api.calcfg.TsdDeviceCalCfgRequest;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

class PayloadCodecRegistryTest {
    private final PayloadCodecRegistry registry = PayloadCodecRegistry.INSTANCE;

    @Test
    void registryShouldMapServerDeviceCalCfgRequestCallType() {
        assertThat(registry.findForCallType(SUPLA_SD_CALL_DEVICE_CALCFG_REQUEST.getValue()))
                .isPresent();
    }

    @Test
    void registryShouldEncodeServerDeviceCalCfgRequestByPayloadClass() {
        TsdDeviceCalCfgRequest request =
                TsdDeviceCalCfgRequest.deviceOtaCommand(
                        5,
                        pl.grzeslowski.jsupla.protocol.api.calcfg.CalCfgCommand
                                .START_FIRMWARE_UPDATE,
                        true);

        assertThat(registry.encode(request)).isPresent();
        assertThat(registry.encode(request).get()).hasSize(request.protoSize());
    }

    @Test
    void registryShouldDecodeDeviceServerCalCfgResultByCallType() {
        byte[] firmwarePayload =
                FirmwareCheckResultCodec.INSTANCE.encode(
                        CalCfgFirmwareCheckResult.of(
                                FirmwareCheckResultCode.UPDATE_AVAILABLE, "4.8.1", "/changes"));
        TdsDeviceCalCfgResult result =
                new TdsDeviceCalCfgResult(
                        11,
                        TsdDeviceCalCfgRequest.DEVICE_CHANNEL_NUMBER,
                        SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE,
                        SUPLA_CALCFG_RESULT_TRUE,
                        0,
                        firmwarePayload.length,
                        firmwarePayload);
        byte[] payload = DeviceCalCfgResultCodec.INSTANCE.encode(result);
        SuplaDataPacket packet =
                new SuplaDataPacket(
                        (short) 28,
                        1,
                        SUPLA_DS_CALL_DEVICE_CALCFG_RESULT.getValue(),
                        payload.length,
                        payload);

        assertThat(registry.decode(packet)).isPresent();
        TdsDeviceCalCfgResult decoded = (TdsDeviceCalCfgResult) registry.decode(packet).get();

        assertThat(decoded.firmwareCheckResult()).isPresent();
        assertThat(decoded.firmwareCheckResult().get().resultCode())
                .isEqualTo(FirmwareCheckResultCode.UPDATE_AVAILABLE);
    }
}
