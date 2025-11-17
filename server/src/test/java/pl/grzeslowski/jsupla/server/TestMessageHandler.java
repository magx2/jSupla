package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.protocol.api.ResultCode.SUPLA_RESULTCODE_TRUE;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

public class TestMessageHandler implements MessageHandler {
    private final Logger logger = LoggerFactory.getLogger(TestMessageHandler.class);
    private SuplaWriter writer;

    @Override
    public void handle(ToServerProto toServerProto) {
        newMessage(toServerProto).ifPresent(writer::write);
    }

    @Override
    public void active(SuplaWriter writer) {
        this.writer = writer;
    }

    @Override
    public void inactive() {
        writer = null;
    }

    @Override
    public void socketException(Throwable exception) {
        logger.warn("Socket exception", exception);
    }

    private Optional<FromServerProto> newMessage(ToServerProto toServerEntity) {
        FromServerProto result =
                switch (toServerEntity) {
                    case SuplaRegisterDevice register ->
                            new SuplaRegisterDeviceResult(
                                    SUPLA_RESULTCODE_TRUE.getValue(),
                                    (byte) 100,
                                    (byte) 5,
                                    (byte) 1);
                    case SuplaRegisterDeviceB register ->
                            new SuplaRegisterDeviceResult(
                                    SUPLA_RESULTCODE_TRUE.getValue(),
                                    (byte) 100,
                                    (byte) 5,
                                    (byte) 1);
                    case SuplaRegisterDeviceC register ->
                            new SuplaRegisterDeviceResult(
                                    SUPLA_RESULTCODE_TRUE.getValue(),
                                    (byte) 100,
                                    (byte) 5,
                                    (byte) 1);
                    case SuplaRegisterDeviceD register ->
                            new SuplaRegisterDeviceResult(
                                    SUPLA_RESULTCODE_TRUE.getValue(),
                                    (byte) 100,
                                    (byte) 5,
                                    (byte) 1);
                    case SuplaRegisterDeviceE register ->
                            new SuplaRegisterDeviceResult(
                                    SUPLA_RESULTCODE_TRUE.getValue(),
                                    (byte) 100,
                                    (byte) 5,
                                    (byte) 1);
                    case SuplaRegisterDeviceF register ->
                            new SuplaRegisterDeviceResult(
                                    SUPLA_RESULTCODE_TRUE.getValue(),
                                    (byte) 100,
                                    (byte) 5,
                                    (byte) 1);
                    case SuplaSetActivityTimeout setTimout ->
                            new SuplaSetActivityTimeoutResult(
                                    setTimout.activityTimeout(),
                                    (short) (setTimout.activityTimeout() - 2),
                                    (short) (setTimout.activityTimeout() + 2));
                    case SuplaDeviceChannelValue suplaDeviceChannelValue -> null;
                    case SuplaPingServer ping -> new SuplaPingServerResult(ping.now());
                    case null, default ->
                            throw new RuntimeException("Unsupported message " + toServerEntity);
                };

        logger.info("Got {}, Sending {}", toServerEntity, result);
        return Optional.ofNullable(result);
    }
}
