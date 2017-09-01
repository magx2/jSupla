package pl.grzeslowski.jsupla.nettytest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactory;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactoryImpl;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.FromServerProtoChannelToResponseChannel;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.FromServerProtoChannelToResponseChannelImpl;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.SuplaDataPacketChannelToFromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.SuplaDataPacketChannelToFromServerProtoChannelImpl;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactoryImpl;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    private SerializersFactory serializersFactory = new SerializersFactoryImpl();
    private final FromServerProtoChannelToResponseChannel fromServerProtoChannelToResponseChannel =
            new FromServerProtoChannelToResponseChannelImpl(serializersFactory);

    private EncoderFactory encoderFactory = new EncoderFactoryImpl();
    private SuplaDataPacketFactory suplaDataPacketFactory = new SuplaDataPacketFactoryImpl(5);
    private final SuplaDataPacketChannelToFromServerProtoChannel suplaDataPacketChannelToFromServerProtoChannel =
            new SuplaDataPacketChannelToFromServerProtoChannelImpl(encoderFactory, suplaDataPacketFactory);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");
        try (NettyServer nettyServer = new NettyServer(new NettyConfig(2016))) {
            logger.info("Run...");

            Flux.from(nettyServer.run()).log();

            TimeUnit.MINUTES.sleep(10);
            logger.warn("End of sleep; closing server");
        }
    }
}
