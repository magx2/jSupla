package pl.grzeslowski.jsupla.nettytest;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.OkRegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.ents.RequestConnection;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageConnection;
import pl.grzeslowski.jsupla.server.ents.SuplaNewConnection;
import pl.grzeslowski.jsupla.server.ents.ToServerProtoConnection;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;
import pl.grzeslowski.jsupla.server.reactor.map.SuplaDataPackageToToServer;
import pl.grzeslowski.jsupla.server.reactor.map.ToServerProtoToRequest;
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

            final Consumer<RequestConnection> suplaConnectionConsumer = requestConnection -> {
                logger.info("Sending OkResponse...");
                final OkRegisterDeviceResponse response = new OkRegisterDeviceResponse(200, 6, 1);
                requestConnection.getChannel().write(response);
            };

            final Consumer<? super SuplaNewConnection> consumer = (Consumer<SuplaNewConnection>) suplaNewConnection -> {
                logger.info("Server.accept(suplaNewConnection) 1");

                final SuplaDataPackageToToServer suplaDataPackageToToServer = new SuplaDataPackageToToServer(null, null);
                final ToServerProtoToRequest toServerProtoToRequest = new ToServerProtoToRequest(null, null, null);

                suplaNewConnection.getFlux()
                        .map(x -> new ToServerProtoConnection(suplaDataPackageToToServer.apply(x.value()), x.getChannel()))
                        .map(x -> new RequestConnection(toServerProtoToRequest.apply(x.value()), x.getChannel()))
                        .subscribe(suplaConnectionConsumer);
            };

            final Consumer<? super Publisher<SuplaDataPackageConnection>> newConsumer = new Consumer<Publisher<SuplaDataPackageConnection>>() {
                @Override
                public void accept(final Publisher<SuplaDataPackageConnection> publisher) {
                    Flux.from(publisher).subscribe(conn -> conn.getChannel().write(null));
                }
            };
            Flux.from(nettyServer.run()).log().subscribe(newConsumer);

            TimeUnit.MINUTES.sleep(10);
            logger.warn("End of sleep; closing server");
        }
    }
}
