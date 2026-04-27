package pl.grzeslowski.jsupla.server;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.CalCfgCommand.SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.CalCfgResult.SUPLA_CALCFG_RESULT_TRUE;
import static pl.grzeslowski.jsupla.protocol.api.FirmwareCheckResultCode.SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;

import io.netty.channel.embedded.EmbeddedChannel;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.calcfg.CalCfgFirmwareCheckResult;
import pl.grzeslowski.jsupla.protocol.api.calcfg.DeviceCalCfgResultCodec;
import pl.grzeslowski.jsupla.protocol.api.calcfg.FirmwareCheckResultCodec;
import pl.grzeslowski.jsupla.protocol.api.calcfg.TdsDeviceCalCfgResult;
import pl.grzeslowski.jsupla.protocol.api.calcfg.TsdDeviceCalCfgRequest;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

class SuplaHandlerCalCfgTest {
    @Test
    void handlerShouldConsumeTypedCalCfgResultPayload() {
        CapturingMessageHandler messageHandler = new CapturingMessageHandler();
        EmbeddedChannel channel =
                new EmbeddedChannel(
                        new SuplaHandler(
                                "test",
                                CallTypeParser.INSTANCE,
                                DecoderFactoryImpl.INSTANCE,
                                EncoderFactoryImpl.INSTANCE,
                                messageHandler));
        byte[] firmwarePayload =
                FirmwareCheckResultCodec.INSTANCE.encode(
                        CalCfgFirmwareCheckResult.of(
                                SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE, "4.8.1", "/changes"));
        byte[] calCfgPayload =
                DeviceCalCfgResultCodec.INSTANCE.encode(
                        new TdsDeviceCalCfgResult(
                                3,
                                TsdDeviceCalCfgRequest.DEVICE_CHANNEL_NUMBER,
                                SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE.getValue(),
                                SUPLA_CALCFG_RESULT_TRUE.getValue(),
                                0,
                                firmwarePayload.length,
                                firmwarePayload));

        channel.writeInbound(
                new SuplaDataPacket(
                        (short) 28,
                        1,
                        SUPLA_DS_CALL_DEVICE_CALCFG_RESULT.getValue(),
                        calCfgPayload.length,
                        calCfgPayload));

        assertThat(messageHandler.handled.get()).isInstanceOf(TdsDeviceCalCfgResult.class);
        TdsDeviceCalCfgResult result = (TdsDeviceCalCfgResult) messageHandler.handled.get();
        assertThat(result.firmwareCheckResult()).isPresent();
        assertThat(result.firmwareCheckResult().get().resultCode())
                .isEqualTo(SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE);
    }

    private static final class CapturingMessageHandler implements MessageHandler {
        private final AtomicReference<ToServerProto> handled = new AtomicReference<>();

        @Override
        public void handle(ToServerProto toServerProto) {
            handled.set(toServerProto);
        }

        @Override
        public void active(SuplaWriter writer) {}

        @Override
        public void inactive() {}

        @Override
        public void socketException(Throwable exception) {}
    }
}
