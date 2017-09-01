package pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndFromServerProtoFlux;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndRequestFlux;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndRequestFluxImpl;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.ResponseChannel;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.FromServerProtoChannelToResponseChannel;
import pl.grzeslowski.jsupla.server.parsers.ParsersFactory;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;

import static java.util.Objects.requireNonNull;

public class FromServerProtoToRequestImpl implements FromServerProtoToRequest {
    private final SerializersFactory serializersFactory;
    private final ParsersFactory parsersFactory;
    private final FromServerProtoChannelToResponseChannel fromServerProtoChannelToResponseChannel;

    public FromServerProtoToRequestImpl(final SerializersFactory serializersFactory,
                                        final ParsersFactory parsersFactory,
                                        final FromServerProtoChannelToResponseChannel fromServerProtoChannelToResponseChannel) {
        this.serializersFactory = serializersFactory;
        this.parsersFactory = parsersFactory;
        this.fromServerProtoChannelToResponseChannel = fromServerProtoChannelToResponseChannel;
    }

    @Override
    public ChannelAndRequestFlux apply(final ChannelAndFromServerProtoFlux in) {
        return new ChannelAndRequestFluxImpl(buildChannel(in.getChannel()), in.getFlux().map(this::mapFlux));
    }

    private ResponseChannel buildChannel(final FromServerProtoChannel channel) {
        return new ResponseChannelImpl(channel);
    }

    private Request mapFlux(final FromServerProto proto) {
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
