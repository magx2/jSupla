package pl.grzeslowski.jsupla.nettytest;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageChannel;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");
        try (NettyServer nettyServer = new NettyServer(new NettyConfig(2016))) {
            logger.info("Run...");

            final Consumer<? super Publisher<SuplaDataPackageChannel>> newConsumer = new Consumer<Publisher<SuplaDataPackageChannel>>() {
                @Override
                public void accept(final Publisher<SuplaDataPackageChannel> publisher) {
                    Flux.from(publisher).subscribe(conn -> conn.getChannel().write(null));
                }
            };
            Flux.from(nettyServer.run()).log().subscribe(newConsumer);

            TimeUnit.MINUTES.sleep(10);
            logger.warn("End of sleep; closing server");
        }
    }
}
