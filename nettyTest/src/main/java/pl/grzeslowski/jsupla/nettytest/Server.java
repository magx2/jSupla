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
import pl.grzeslowski.jsupla.server.SuplaStreamImpl;
import pl.grzeslowski.jsupla.server.entities.requests.ds.ActivityTimeoutRequest;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequest;
import pl.grzeslowski.jsupla.server.entities.responses.ActivityTimeoutResponse;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.OkRegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactoryImpl;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers.FromServerProtoToRequest;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers.FromServerProtoToRequestImpl;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers.SuplaDataPacketToFromServerProto;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers.SuplaDataPacketToFromServerProtoImpl;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.FromServerProtoChannelToResponseChannelImpl;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactoryImpl;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactoryImpl;

import java.util.concurrent.TimeUnit;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);
    private final SuplaDataPacketToFromServerProto x =
            new SuplaDataPacketToFromServerProtoImpl(
                                                            new EncoderFactoryImpl(),
                                                            new DecoderFactoryImpl(PrimitiveDecoderImpl.INSTANCE),
                                                            new CallTypeParserImpl(),
                                                            new SuplaDataPacketFactoryImpl(5)
            );
    private final SerializersFactory serializersFactory = new SerializersFactoryImpl();
    private final FromServerProtoToRequest y =
            new FromServerProtoToRequestImpl(
                                                    serializersFactory,
                                                    new ParsersFactoryImpl(),
                                                    new FromServerProtoChannelToResponseChannelImpl(serializersFactory)
            );

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");

        SelfSignedCertificate ssc = new SelfSignedCertificate();
        SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

        final SuplaStreamImpl suplaStream =
                new SuplaStreamImpl(() -> new NettyServer(new NettyConfig(2016, sslCtx)), x, y);

        suplaStream.fullStream().log().subscribe(in -> in.getFlux().log().subscribe(request -> {
            if (request instanceof RegisterDeviceRequest) {
                logger.info("Sending register OK");
                in.getChannel().write(new OkRegisterDeviceResponse(200, 5, 1));
            } else if (request instanceof ActivityTimeoutRequest) {
                final ActivityTimeoutRequest a = (ActivityTimeoutRequest) request;
                in.getChannel().write(new ActivityTimeoutResponse(a.getActivityTimeout(), 1, 255));
            } else {
                logger.warn("Doesn't know this type of request {}", request.getClass().getSimpleName());
            }
        }));

        TimeUnit.MINUTES.sleep(10);
        logger.warn("End of sleep; closing server");
    }
}
