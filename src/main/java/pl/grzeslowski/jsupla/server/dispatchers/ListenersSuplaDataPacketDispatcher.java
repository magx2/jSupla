package pl.grzeslowski.jsupla.server.dispatchers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.consts.CallType;
import pl.grzeslowski.jsupla.proto.decoders.Decoder;
import pl.grzeslowski.jsupla.proto.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.proto.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.proto.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.listeners.ListenersFactory;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("WeakerAccess")
public class ListenersSuplaDataPacketDispatcher implements SuplaDataPacketDispatcher {
    private final Logger logger = LoggerFactory.getLogger(ListenersSuplaDataPacketDispatcher.class);
    
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;

    private final ParsersFactory parsersFactory;
    private final SerializersFactory serializersFactory;

    private final ListenersFactory listenersFactory;

    public ListenersSuplaDataPacketDispatcher(DecoderFactory decoderFactory, EncoderFactory encoderFactory,
                                              ParsersFactory parsersFactory, SerializersFactory serializersFactory,
                                              ListenersFactory listenersFactory) {
        this.decoderFactory = requireNonNull(decoderFactory);
        this.encoderFactory = requireNonNull(encoderFactory);
        this.parsersFactory = requireNonNull(parsersFactory);
        this.serializersFactory = requireNonNull(serializersFactory);
        this.listenersFactory = requireNonNull(listenersFactory);
    }

    @Override
    public Optional<TSuplaDataPacket> dispatch(TSuplaDataPacket dataPacket) {
        return CallType.findByValue(dataPacket.callType)
                .map(this::getDecoderForCallType)
                .map(decoder -> this.decode(decoder, dataPacket))
                .map(this::parse)
                .flatMap(this::onRequest)
                .map(this::serialize)
                .map(this::encode);
    }

    protected Decoder<DeviceServer> getDecoderForCallType(CallType callType) {
        logger.trace("ListenersSuplaDataPacketDispatcher.getDecoderForCallType({})", callType);
        return decoderFactory.getDecoderForCallType(callType);
    }

    protected DeviceServer decode(Decoder<DeviceServer> deviceServerDecoder, TSuplaDataPacket dataPacket) {
        logger.trace("ListenersSuplaDataPacketDispatcher.decode({}, {})", deviceServerDecoder, dataPacket);
        return deviceServerDecoder.decode(dataPacket);
    }

    protected Request parse(DeviceServer deviceServer) {
        logger.trace("ListenersSuplaDataPacketDispatcher.parse({})", deviceServer);
        return parsersFactory.getParser(deviceServer).parse(deviceServer);
    }

    protected Optional<Response> onRequest(Request request) {
        logger.trace("ListenersSuplaDataPacketDispatcher.onRequest({})", request);
        return listenersFactory.getRequestListener(request).onRequest(request);
    }

    protected ServerDevice serialize(Response response) {
        logger.trace("ListenersSuplaDataPacketDispatcher.serialize({})", response);
        return serializersFactory.getSerializerForResponse(response).serialize(response);
    }

    protected TSuplaDataPacket encode(ServerDevice proto) {
        logger.trace("ListenersSuplaDataPacketDispatcher.encode({})", proto);
        return encoderFactory.getEncoderForServerDevice(proto).encode(proto);
    }
}
