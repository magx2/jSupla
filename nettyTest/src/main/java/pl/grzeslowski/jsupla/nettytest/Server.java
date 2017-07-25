package pl.grzeslowski.jsupla.nettytest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.server.listeners.Listeners;
import pl.grzeslowski.jsupla.server.listeners.SuplaDataPacketListener;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");
        final Listeners listeners = new Listeners().setSuplaDataPacketListener(new SuplaDataPacketListener() {
            @Override
            public void onSuplaDataPacket(TSuplaDataPacket dataPacket) {
                logger.info("Got: {}", dataPacket);
            }
        });
        try (NettyServer nettyServer = new NettyServer(new NettyConfig(2016), listeners)) {
            logger.info("Run...");
            nettyServer.run();

            while (true) {

            }
        }
    }

}
