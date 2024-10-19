package pl.grzeslowski.jsupla.server;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.api.MessageHandler;
import pl.grzeslowski.jsupla.server.api.ServerFactory;
import pl.grzeslowski.jsupla.server.api.ServerProperties;
import pl.grzeslowski.jsupla.server.api.Writer;
import pl.grzeslowski.jsupla.server.netty.NettyServerFactory;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static pl.grzeslowski.jsupla.protocol.api.ResultCode.SUPLA_RESULTCODE_TRUE;
import static pl.grzeslowski.jsupla.server.netty.NettyServerFactory.PORT;
import static pl.grzeslowski.jsupla.server.netty.NettyServerFactory.SSL_CTX;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final int PROPER_AES_KEY_SIZE = 2147483647;

    public static void main(String[] args) throws Exception {
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");

        int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
        if (maxKeySize < PROPER_AES_KEY_SIZE) {
            logger.warn(
                "AES key size is too small, {} < {}! Probably you need to enable unlimited crypto",
                maxKeySize,
                PROPER_AES_KEY_SIZE);
        }

        final ServerFactory factory = buildServerFactory();
        pl.grzeslowski.jsupla.server.api.Server server = factory.createNewServer(
            buildServerProperties(),
            (socketChannel) -> new MessageHandler() {
                Writer writer;

                @Override
                public void handle(ToServerProto toServerProto) {
                    Server.this.newMessage(toServerProto)
                        .ifPresent(proto -> writer.write(proto));
                }

                @Override
                public void active(Writer writer) {
                    this.writer = writer;
                }

                @Override
                public void inactive() {

                }
            });

        logger.info("Started");
        TimeUnit.MINUTES.sleep(10);
        logger.warn("End of sleep; closing server");
        server.close();
    }

    private Optional<FromServerProto> newMessage(ToServerProto toServerEntity) {
        FromServerProto result;

        if (toServerEntity instanceof SuplaRegisterDevice) {
            val register = (SuplaRegisterDevice) toServerEntity;
            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        } else if (toServerEntity instanceof SuplaRegisterDeviceB) {
            val register = (SuplaRegisterDeviceB) toServerEntity;
            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        } else if (toServerEntity instanceof SuplaRegisterDeviceC) {
            val register = (SuplaRegisterDeviceC) toServerEntity;
            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        } else if (toServerEntity instanceof SuplaRegisterDeviceD) {
            val register = (SuplaRegisterDeviceD) toServerEntity;
            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        } else if (toServerEntity instanceof SuplaRegisterDeviceE) {
            val register = (SuplaRegisterDeviceE) toServerEntity;
            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        } else if (toServerEntity instanceof SuplaRegisterDeviceF) {
            val register = (SuplaRegisterDeviceF) toServerEntity;
            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        } else if (toServerEntity instanceof SuplaSetActivityTimeout) {
            val setTimout = (SuplaSetActivityTimeout) toServerEntity;
            result = new SuplaSetActivityTimeoutResult(setTimout.activityTimeout, (short) (setTimout.activityTimeout - 2), (short) (setTimout.activityTimeout + 2));
        } else if (toServerEntity instanceof SuplaDeviceChannelValue) {
            result = null;
        } else if (toServerEntity instanceof SuplaPingServer) {
            val ping = (SuplaPingServer) toServerEntity;
            result = new SuplaPingServerResult(ping.now);
        } else {
            throw new RuntimeException("Unsupported message " + toServerEntity);
        }

        logger.info("Got {}, Sending {}", toServerEntity, result);
        return Optional.ofNullable(result);
    }

    private ServerFactory buildServerFactory() {
        return new NettyServerFactory(
            CallTypeParser.INSTANCE,
            DecoderFactoryImpl.INSTANCE,
            EncoderFactoryImpl.INSTANCE);
    }

    private ServerProperties buildServerProperties() throws CertificateException, SSLException {
        return ServerProperties.fromList(Arrays.asList(PORT, 2016, SSL_CTX, buildSslContext()));
    }

    private SslContext buildSslContext() throws CertificateException, SSLException {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
            .protocols("TLSv1.3", "TLSv1.2", "TLSv1")
            .build();
    }
}
