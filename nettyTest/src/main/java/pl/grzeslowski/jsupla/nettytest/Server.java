package pl.grzeslowski.jsupla.nettytest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.consts.ProtoConsts;
import pl.grzeslowski.jsupla.proto.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.proto.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.server.SImpleDataPacketIdGenerator;
import pl.grzeslowski.jsupla.server.dispatchers.ListenersSuplaDataPacketDispatcher;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequest;
import pl.grzeslowski.jsupla.server.entities.responses.RegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.listeners.ListenersFactoryImpl;
import pl.grzeslowski.jsupla.server.listeners.RequestListener;
import pl.grzeslowski.jsupla.server.netty.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.NettyServer;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactoryImpl;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactoryImpl;

import java.util.Optional;

import static java.util.Optional.of;

public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        new Server().run();
    }

    private void run() throws Exception {
        logger.info("Starting...");
        try (NettyServer nettyServer = new NettyServer(new NettyConfig(2016),
                new ListenersSuplaDataPacketDispatcher(
                        new DecoderFactoryImpl(),
                        new EncoderFactoryImpl(6, new SImpleDataPacketIdGenerator()),
                        new ParsersFactoryImpl(),
                        new SerializersFactoryImpl(),
                        new ListenersFactoryImpl(new DeviceRegisterListener())))) {
            logger.info("Run...");
            nettyServer.run();

            while (true) {

            }
        }
    }


    private static class DeviceRegisterListener implements RequestListener<DeviceRegisterRequest, RegisterDeviceResponse> {
        private final Logger logger = LoggerFactory.getLogger(DeviceRegisterListener.class);

        @Override
        public Optional<RegisterDeviceResponse> onRequest(DeviceRegisterRequest request) {
            logger.info("Got {} returning response", request);
            return of(new RegisterDeviceResponse(ProtoConsts.SUPLA_SD_CALL_REGISTER_DEVICE_RESULT, 100, 6, 2));
        }
    }
}
