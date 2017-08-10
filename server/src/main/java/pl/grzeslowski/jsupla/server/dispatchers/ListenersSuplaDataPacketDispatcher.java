package pl.grzeslowski.jsupla.server.dispatchers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.listeners.ListenersFactory;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Stream.concat;

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
    public Optional<SuplaDataPacket> dispatch(SuplaDataPacket dataPacket) {
        getAllValuesThatCanComeToServer();

        return getAllValuesThatCanComeToServer().filter(callType -> callType.getValue() == dataPacket.callType)
                .findFirst()
                .map(this::getDecoderForCallType)
                .map(decoder -> this.decode(decoder, dataPacket))
                .map(this::parse)
                .flatMap(this::onRequest)
                .map(this::serialize)
                .map(this::encode);
    }

    private static Stream<CallType> getAllValuesThatCanComeToServer() {
        final Stream<ClientServerCallType> cs = Arrays.stream(ClientServerCallType.values());
        final Stream<DeviceServerCallType> ds = Arrays.stream(DeviceServerCallType.values());
        final Stream<DeviceClientServerCallType> dcs = Arrays.stream(DeviceClientServerCallType.values());
        return concat(cs, concat(ds, dcs));
    }

    protected Decoder<DeviceServer> getDecoderForCallType(CallType callType) {
        logger.trace("ListenersSuplaDataPacketDispatcher.getDecoderForCallType({})", callType);
        return decoderFactory.getDecoderForCallType(callType);
    }

    protected DeviceServer decode(Decoder<DeviceServer> deviceServerDecoder, SuplaDataPacket dataPacket) {
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

    protected SuplaDataPacket encode(ServerDevice proto) {
        logger.trace("ListenersSuplaDataPacketDispatcher.encode({})", proto);
        //        return encoderFactory.getEncoderForServerDevice(proto).encode(proto);
        throw new UnsupportedOperationException(); // TODO
    }
}
