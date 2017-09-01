package pl.grzeslowski.jsupla.server.ents.channels.channelmappers;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.ResponseChannel;
import pl.grzeslowski.jsupla.server.serializers.SerializersFactory;

import static java.util.Objects.requireNonNull;

public class FromServerProtoChannelToResponseChannelImpl implements FromServerProtoChannelToResponseChannel {
    private final SerializersFactory serializersFactory;

    public FromServerProtoChannelToResponseChannelImpl(final SerializersFactory serializersFactory) {
        this.serializersFactory = requireNonNull(serializersFactory);
    }

    @Override
    public ResponseChannel apply(final FromServerProtoChannel fromServerProtoChannel) {
        return new ResponseChannelImpl(fromServerProtoChannel);
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
