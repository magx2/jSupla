package pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactory;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndFromServerProtoFlux;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndFromServerProtoFluxImpl;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SuplaDataPacketToFromServerProtoImpl implements SuplaDataPacketToFromServerProto {
    private final EncoderFactory encoderFactory;
    private final DecoderFactory decoderFactory;
    private final CallTypeParser callTypeParser;
    private final SuplaDataPacketFactory suplaDataPacketFactory;

    public SuplaDataPacketToFromServerProtoImpl(final EncoderFactory encoderFactory,
                                                final DecoderFactory decoderFactory,
                                                final CallTypeParser callTypeParser,
                                                final SuplaDataPacketFactory suplaDataPacketFactory) {
        this.encoderFactory = requireNonNull(encoderFactory);
        this.decoderFactory = requireNonNull(decoderFactory);
        this.callTypeParser = requireNonNull(callTypeParser);
        this.suplaDataPacketFactory = requireNonNull(suplaDataPacketFactory);
    }

    @Override
    public ChannelAndFromServerProtoFlux apply(final ChannelAndSuplaDataPackageFlux in) {
        return new ChannelAndFromServerProtoFluxImpl(buildChannel(in.getChannel()), in.getFlux().map(this::mapFlux));
    }

    private FromServerProtoChannel buildChannel(final SuplaDataPacketChannel channel) {
        return new FromServerProtoChannelImpl(channel);
    }

    private FromServerProto mapFlux(final SuplaDataPacket suplaDataPacket) {
        final long callType = suplaDataPacket.callType;
        final CallType parse = callTypeParser.parse(callType).orElseThrow(() ->
                                                                                  new RuntimeException(format("There is not call type with number %s!", callType)));

        final ProtoWithSize decode = decoderFactory.getDecoder(parse).decode(suplaDataPacket.data);
        return (FromServerProto) decode;
    }

    private class FromServerProtoChannelImpl implements FromServerProtoChannel {
        private final SuplaDataPacketChannel suplaDataPacketChannel;

        private FromServerProtoChannelImpl(final SuplaDataPacketChannel suplaDataPacketChannel) {
            this.suplaDataPacketChannel = requireNonNull(suplaDataPacketChannel);
        }

        @Override
        public void write(final FromServerProto data) {
            final byte[] encode = encoderFactory.getEncoder(data).encode(data);
            final CallType callType = data.callType();
            final SuplaDataPacket suplaDataPacket = suplaDataPacketFactory.newSuplaDataPacketFactory(encode, callType);
            suplaDataPacketChannel.write(suplaDataPacket);
        }
    }
}
