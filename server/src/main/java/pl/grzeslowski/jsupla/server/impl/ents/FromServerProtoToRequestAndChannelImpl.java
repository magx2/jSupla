package pl.grzeslowski.jsupla.server.impl.ents;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.api.entities.requests.Request;
import pl.grzeslowski.jsupla.server.api.ents.FromServerProtoToRequestAndChannel;
import pl.grzeslowski.jsupla.server.api.ents.channels.ResponseChannel;
import pl.grzeslowski.jsupla.server.api.ents.channels.channelmappers.FromServerProtoChannelToResponseChannel;
import pl.grzeslowski.jsupla.server.api.parsers.ParsersFactory;

import static java.util.Objects.requireNonNull;

public class FromServerProtoToRequestAndChannelImpl implements FromServerProtoToRequestAndChannel {
    private final ParsersFactory parsersFactory;
    private final FromServerProtoChannelToResponseChannel fromServerProtoChannelToResponseChannel;

    public FromServerProtoToRequestAndChannelImpl(final ParsersFactory parsersFactory,
                                                  final FromServerProtoChannelToResponseChannel from) {
        this.parsersFactory = requireNonNull(parsersFactory);
        this.fromServerProtoChannelToResponseChannel = requireNonNull(from);
    }

    @Override
    public RequestAndChannel apply(final FromServerProtoDataAndChannel fromServerProtoDataAndChannel) {
        final FromServerProto proto = fromServerProtoDataAndChannel.getProto();
        final Request request = parsersFactory.getParser(proto).parse(proto);

        final ResponseChannel channel =
                fromServerProtoChannelToResponseChannel.apply(fromServerProtoDataAndChannel.getChannel());

        return new RequestAndChannel(request, channel);
    }
}
