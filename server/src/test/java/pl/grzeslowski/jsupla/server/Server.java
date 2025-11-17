package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.server.NettyConfig.SUPLA_HTTPS_PORT;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;

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
                    "AES key size is too small, {} < {}! Probably you need to enable unlimited"
                            + " crypto",
                    maxKeySize,
                    PROPER_AES_KEY_SIZE);
        }

        val server =
                new NettyServer(
                        new NettyConfig(SUPLA_HTTPS_PORT, buildSslContext()),
                        CallTypeParser.INSTANCE,
                        DecoderFactoryImpl.INSTANCE,
                        EncoderFactoryImpl.INSTANCE,
                        new TestMessageHandlerFactory());

        logger.info("Started");
        TimeUnit.MINUTES.sleep(10);
        logger.warn("End of sleep; closing server");
        server.close();
    }

    private SslContext buildSslContext() throws CertificateException, SSLException {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
                .protocols("TLSv1.3", "TLSv1.2", "TLSv1")
                .build();
    }
}
