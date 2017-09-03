package pl.grzeslowski.jsupla.server.impl.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;
import pl.grzeslowski.jsupla.server.api.entities.responses.Response;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndRequestFlux;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndToServerProtoFlux;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.mappers.FromServerProtoToRequest;
import pl.grzeslowski.jsupla.server.api.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.api.ents.channels.ResponseChannel;
import pl.grzeslowski.jsupla.server.api.ents.channels.channelmappers.FromServerProtoChannelToResponseChannel;
import pl.grzeslowski.jsupla.server.api.parsers.ParsersFactory;
import pl.grzeslowski.jsupla.server.api.serializers.SerializersFactory;
import pl.grzeslowski.jsupla.server.impl.ents.channelandpublisher.ChannelAndRequestFluxImpl;

import static java.util.Objects.requireNonNull;

public class FromServerProtoToRequestImpl implements FromServerProtoToRequest {
    private final SerializersFactory serializersFactory;
    private final ParsersFactory parsersFactory;
    private final FromServerProtoChannelToResponseChannel fromServerProtoChannelToResponseChannel;

    public FromServerProtoToRequestImpl(final SerializersFactory serializersFactory,
                                        final ParsersFactory parsersFactory,
                                        final FromServerProtoChannelToResponseChannel from) {
        this.serializersFactory = serializersFactory;
        this.parsersFactory = parsersFactory;
        this.fromServerProtoChannelToResponseChannel = from;
    }

    @Override
    public ChannelAndRequestFlux apply(final ChannelAndToServerProtoFlux in) {
        return new ChannelAndRequestFluxImpl(buildChannel(in.getChannel()), in.getFlux().map(this::mapFlux));
    }

    private ResponseChannel buildChannel(final FromServerProtoChannel channel) {
        return new ResponseChannelImpl(channel);
    }

    private Request mapFlux(final ToServerProto proto) {
        return parsersFactory.getParser(proto).parse(proto);
    }

    private class ResponseChannelImpl implements ResponseChannel {
        private final FromServerProtoChannel fromServerProtoChannel;

        private ResponseChannelImpl(final FromServerProtoChannel fromServerProtoChannel) {
            this.fromServerProtoChannel = requireNonNull(fromServerProtoChannel);
        }

        @Override
        public void write(final Response data) {
            final FromServerProto serialize = serializersFactory.getSerializerForResponse(data).serialize(data);
            fromServerProtoChannel.write(serialize);
        }
    }
}
