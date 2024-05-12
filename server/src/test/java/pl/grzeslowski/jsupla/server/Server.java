package pl.grzeslowski.jsupla.server;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.api.Channel;
import pl.grzeslowski.jsupla.server.api.ServerFactory;
import pl.grzeslowski.jsupla.server.api.ServerProperties;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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

        buildSslContext();

        final ServerFactory factory = buildServerFactory();
        pl.grzeslowski.jsupla.server.api.Server server = factory.createNewServer(buildServerProperties());

        server.getNewChannelsPipe().subscribe(this::newChannel);

        logger.info("Started");
        TimeUnit.MINUTES.sleep(10);
        logger.warn("End of sleep; closing server");
        server.close();
    }

    private void newChannel(final Channel channel) {
        logger.info("New channel {}", channel);
        channel.getMessagePipe().subscribe(toServerEntity -> newMessage(toServerEntity, channel));
    }

    private void newMessage(ToServerProto toServerEntity, Channel channel) {
        FromServerProto result;

//        if (toServerEntity instanceof RegisterDeviceTrait) {
//            val register = (RegisterDeviceTrait) toServerEntity;
//            result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
//        } else if (toServerEntity instanceof SuplaSetActivityTimeout) {
//            val setTimout = (SuplaSetActivityTimeout) toServerEntity;
//            result = new SuplaSetActivityTimeoutResult(setTimout.activityTimeout, (short) (setTimout.activityTimeout - 2), (short) (setTimout.activityTimeout + 2));
//        } else if (toServerEntity instanceof SuplaDeviceChannelValue) {
//            result = null;
//        } else if (toServerEntity instanceof SuplaPingServer) {
//            val ping = (SuplaPingServer) toServerEntity;
//            result = new SuplaPingServerResultClient(ping.timeval);
//        } else {
//            throw new RuntimeException("Unsupported message " + toServerEntity);
//        }

//        logger.info("Got {}, Sending {}", toServerEntity, result);
//        if (result != null) {
//            channel.write(just(result)).subscribe();
//        }
    }

    private ServerFactory buildServerFactory() {
//        return new NettyServerFactory(
//            new CallTypeParserImpl(),
//            DecoderFactoryImpl.INSTANCE,
//            EncoderFactoryImpl.INSTANCE);
        throw new UnsupportedOperationException("Server.buildServerFactory()");
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
