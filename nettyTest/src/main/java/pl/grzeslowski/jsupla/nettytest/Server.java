package pl.grzeslowski.jsupla.nettytest;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.protocol.impl.calltypes.CallTypeParserImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.server.api.Channel;
import pl.grzeslowski.jsupla.server.api.ServerFactory;
import pl.grzeslowski.jsupla.server.api.ServerProperties;
import pl.grzeslowski.jsupla.server.netty.api.NettyServerFactory;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static pl.grzeslowski.jsupla.protocol.api.ResultCode.SUPLA_RESULTCODE_TRUE;
import static pl.grzeslowski.jsupla.server.netty.api.NettyServerFactory.PORT;
import static pl.grzeslowski.jsupla.server.netty.api.NettyServerFactory.SSL_CTX;
import static reactor.core.publisher.Flux.just;

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
        logger.info("New message {}", toServerEntity);

        val result = new SuplaRegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), (byte) 100, (byte) 5, (byte) 1);
        channel.write(just(result)).subscribe(System.out::println);
    }

    private ServerFactory buildServerFactory() {
        return new NettyServerFactory(
            new CallTypeParserImpl(),
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
