package pl.grzeslowski.jsupla.server.dispatchers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
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
import static pl.grzeslowski.jsupla.Preconditions.size;

@SuppressWarnings("WeakerAccess")
public class ListenersSuplaDataPacketDispatcher implements SuplaDataPacketDispatcher {
    private final Logger logger = LoggerFactory.getLogger(ListenersSuplaDataPacketDispatcher.class);

    private final int version;

    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;

    private final ParsersFactory parsersFactory;
    private final SerializersFactory serializersFactory;

    private final ListenersFactory listenersFactory;

    public ListenersSuplaDataPacketDispatcher(int version, DecoderFactory decoderFactory, EncoderFactory encoderFactory,
                                              ParsersFactory parsersFactory, SerializersFactory serializersFactory,
                                              ListenersFactory listenersFactory) {
        this.version = size(version, 0, 255);
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
                .map(this::encode)
                .map(this::newSuplaDataPacket);
    }

    private SuplaDataPacket newSuplaDataPacket(BytesWithCallType bytesWithCallType) {
        return new SuplaDataPacket((short) version,
                1L, // TODO return next int
                bytesWithCallType.callType.getValue(),
                bytesWithCallType.data.length,
                bytesWithCallType.data);
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

    protected ProtoWithSize decode(Decoder<DeviceServer> deviceServerDecoder, SuplaDataPacket dataPacket) {
        logger.trace("ListenersSuplaDataPacketDispatcher.decode({}, {})", deviceServerDecoder, dataPacket);
        return deviceServerDecoder.decode(dataPacket);
    }

    protected Request parse(ProtoWithSize deviceServer) {
        logger.trace("ListenersSuplaDataPacketDispatcher.parse({})", deviceServer);
        return parsersFactory.getParser(deviceServer).parse(deviceServer);
    }

    protected Optional<Response> onRequest(Request request) {
        logger.trace("ListenersSuplaDataPacketDispatcher.onRequest({})", request);
        return listenersFactory.getRequestListener(request).onRequest(request);
    }

    protected ProtoToSend serialize(Response response) {
        logger.trace("ListenersSuplaDataPacketDispatcher.serialize({})", response);
        return serializersFactory.getSerializerForResponse(response).serialize(response);
    }

    protected BytesWithCallType encode(ProtoToSend proto) {
        logger.trace("ListenersSuplaDataPacketDispatcher.encode({})", proto);
        final byte[] data = encoderFactory.getEncoderForServerDevice(proto).encode(proto);
        return new BytesWithCallType(data, proto.callType());
    }

    protected static final class BytesWithCallType {
        protected final byte[] data;
        protected final CallType callType;

        public BytesWithCallType(byte[] data, CallType callType) {
            this.data = data;
            this.callType = requireNonNull(callType);
        }
    }
}
