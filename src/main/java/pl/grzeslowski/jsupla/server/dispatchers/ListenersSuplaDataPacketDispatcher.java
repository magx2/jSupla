package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.consts.CallType;
import pl.grzeslowski.jsupla.proto.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.proto.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.server.listeners.ListenersFactory;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class ListenersSuplaDataPacketDispatcher implements SuplaDataPacketDispatcher {
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
                .map(decoderFactory::getDecoderForCallType)
                .map(codec -> codec.decode(dataPacket))
                .map(proto -> parsersFactory.getParser(proto).parse(proto))
                .flatMap(request -> listenersFactory.getRequestListener(request).onRequest(request))
                .map(response -> serializersFactory.getSerializerForResponse(response).serialize(response))
                .map(proto -> encoderFactory.getEncoderForServerDevice(proto).encode(proto));
    }
}
