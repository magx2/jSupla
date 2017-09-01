package pl.grzeslowski.jsupla.server.ents.channels.channelmappers;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPacketFactory;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

import static java.util.Objects.requireNonNull;

public class SuplaDataPacketChannelToFromServerProtoChannelImpl
        implements SuplaDataPacketChannelToFromServerProtoChannel {
    private final EncoderFactory encoderFactory;
    private final SuplaDataPacketFactory suplaDataPacketFactory;

    public SuplaDataPacketChannelToFromServerProtoChannelImpl(final EncoderFactory encoderFactory,
                                                              final SuplaDataPacketFactory suplaDataPacketFactory) {
        this.encoderFactory = requireNonNull(encoderFactory);
        this.suplaDataPacketFactory = requireNonNull(suplaDataPacketFactory);
    }

    @Override
    public FromServerProtoChannel apply(final SuplaDataPacketChannel suplaDataPacketChannel) {
        return new FromServerProtoChannelImpl(suplaDataPacketChannel);
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
