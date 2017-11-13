package pl.grzeslowski.jsupla.nettytest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        throw new UnsupportedOperationException("Server.run()");
//        logger.info("Starting...");
//
//        SelfSignedCertificate ssc = new SelfSignedCertificate();
//        SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
//
//
//
//        TimeUnit.MINUTES.sleep(10);
//        logger.warn("End of sleep; closing server");
    }
}
