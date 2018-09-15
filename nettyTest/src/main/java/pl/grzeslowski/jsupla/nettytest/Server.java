package pl.grzeslowski.jsupla.nettytest;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.impl.calltypes.CallTypeParserImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToServerEntity;
import pl.grzeslowski.jsupla.server.api.Channel;
import pl.grzeslowski.jsupla.server.api.ServerFactory;
import pl.grzeslowski.jsupla.server.api.ServerProperties;
import pl.grzeslowski.jsupla.server.netty.api.NettyServerFactory;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static pl.grzeslowski.jsupla.protocol.api.ResultCode.SUPLA_RESULTCODE_TRUE;
import static pl.grzeslowski.jsupla.protocoljava.api.ProtocolJavaContext.PROTOCOL_JAVA_CONTEXT;
import static pl.grzeslowski.jsupla.server.netty.api.NettyServerFactory.PORT;
import static pl.grzeslowski.jsupla.server.netty.api.NettyServerFactory.SSL_CTX;
import static reactor.core.publisher.Flux.just;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");

        buildSslContext();

        final ServerFactory factory = buildServerFactory();
        pl.grzeslowski.jsupla.server.api.Server server = factory.createNewServer(buildServerProperties());

        server.getNewChannelsPipe().subscribe(this::newChannel);

        TimeUnit.MINUTES.sleep(10);
        logger.warn("End of sleep; closing server");
        server.close();
    }

    private void newChannel(final Channel channel) {
        logger.info("New channel {}", channel);
        channel.getMessagePipe().subscribe(toServerEntity -> newMessage(toServerEntity, channel));
    }

    private void newMessage(final ToServerEntity toServerEntity, Channel channel) {
        logger.info("New message {}", toServerEntity);

        final RegisterDeviceResult result = new RegisterDeviceResult(SUPLA_RESULTCODE_TRUE.getValue(), 100, 5, 1);
        channel.write(just(result)).subscribe(System.out::println);
    }

    @SuppressWarnings("unchecked")
    private ServerFactory buildServerFactory() {
        return new NettyServerFactory(
                new CallTypeParserImpl(),
                new DecoderFactoryImpl(new PrimitiveDecoderImpl()),
                new EncoderFactoryImpl(new PrimitiveEncoderImpl()),
                PROTOCOL_JAVA_CONTEXT.getService(Parser.class),
                PROTOCOL_JAVA_CONTEXT.getService(Serializer.class));
    }

    private ServerProperties buildServerProperties() throws CertificateException, SSLException {
        return ServerProperties.fromList(Arrays.asList(PORT, 2016, SSL_CTX, buildSslContext()));
    }

    private SslContext buildSslContext() throws CertificateException, SSLException {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
    }
}
