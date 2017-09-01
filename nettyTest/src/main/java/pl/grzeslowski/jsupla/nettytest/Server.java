package pl.grzeslowski.jsupla.nettytest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.impl.calltypes.CallTypeParserImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.ents.FromServerProtoToRequestAndChannel;
import pl.grzeslowski.jsupla.server.ents.FromServerProtoToRequestAndChannelImpl;
import pl.grzeslowski.jsupla.server.ents.RequestAndChannel;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactory;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactoryImpl;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketToFromServerProtoAndChannel;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketToFromServerProtoAndChannelImpl;
import pl.grzeslowski.jsupla.server.ents.channels.ResponseChannel;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.FromServerProtoChannelToResponseChannel;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.FromServerProtoChannelToResponseChannelImpl;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.SuplaDataPacketChannelToFromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.SuplaDataPacketChannelToFromServerProtoChannelImpl;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactory;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactoryImpl;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactoryImpl;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    private SerializersFactory serializersFactory = new SerializersFactoryImpl();
    private final FromServerProtoChannelToResponseChannel fromServerProtoChannelToResponseChannel =
            new FromServerProtoChannelToResponseChannelImpl(serializersFactory);

    private EncoderFactory encoderFactory = new EncoderFactoryImpl();
    private SuplaDataPacketFactory suplaDataPacketFactory = new SuplaDataPacketFactoryImpl(5);
    private final SuplaDataPacketChannelToFromServerProtoChannel suplaDataPacketChannelToFromServerProtoChannel =
            new SuplaDataPacketChannelToFromServerProtoChannelImpl(encoderFactory, suplaDataPacketFactory);

    private DecoderFactory decoderFactory = new DecoderFactoryImpl(PrimitiveDecoderImpl.INSTANCE);
    private final SuplaDataPacketToFromServerProtoAndChannel suplaDataPacketToFromServerProtoAndChannel =
            new SuplaDataPacketToFromServerProtoAndChannelImpl(decoderFactory, new CallTypeParserImpl(),
                                                                      suplaDataPacketChannelToFromServerProtoChannel);

    private ParsersFactory parserFactory = new ParsersFactoryImpl();
    private final FromServerProtoToRequestAndChannel fromServerProtoToRequestAndChannel =
            new FromServerProtoToRequestAndChannelImpl(parserFactory, fromServerProtoChannelToResponseChannel);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");
        try (NettyServer nettyServer = new NettyServer(new NettyConfig(2016))) {
            logger.info("Run...");

            final Consumer<? super Flux<RequestAndChannel>> consumer = new Consumer<Flux<RequestAndChannel>>() {
                @Override
                public void accept(final Flux<RequestAndChannel> flux) {
                    flux.subscribe(new Consumer<RequestAndChannel>() {
                        @Override
                        public void accept(final RequestAndChannel requestAndChannel) {
                            final Request request = requestAndChannel.getRequest();
                            final ResponseChannel channel = requestAndChannel.getChannel();

                            // TODO handle...
                        }
                    });
                }
            };

            Flux.from(nettyServer.run()).log()
                    .map(Flux::from)
                    .map(flux -> flux.map(suplaDataPacketToFromServerProtoAndChannel))
                    .map(flux -> flux.map(fromServerProtoToRequestAndChannel))
                    .subscribe(consumer);

            TimeUnit.MINUTES.sleep(10);
            logger.warn("End of sleep; closing server");
        }
    }
}
