package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.ents.channels.channelmappers.SuplaDataPacketChannelToFromServerProtoChannel;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SuplaDataPacketToFromServerProtoAndChannelImpl implements SuplaDataPacketToFromServerProtoAndChannel {
    private final DecoderFactory decoderFactory;
    private final CallTypeParser callTypeParser;
    private final SuplaDataPacketChannelToFromServerProtoChannel suplaDataPacketChannelToFromServerProtoChannel;

    public SuplaDataPacketToFromServerProtoAndChannelImpl(final DecoderFactory decoderFactory,
                                                          final CallTypeParser callTypeParser,
                                                          final SuplaDataPacketChannelToFromServerProtoChannel
                                                                  suplaDataPacketChannelToFromServerProtoChannel) {
        this.decoderFactory = requireNonNull(decoderFactory);
        this.callTypeParser = requireNonNull(callTypeParser);
        this.suplaDataPacketChannelToFromServerProtoChannel =
                requireNonNull(suplaDataPacketChannelToFromServerProtoChannel);
    }

    @Override
    public FromServerProtoDataAndChannel apply(final SuplaDataPackageAndChannel suplaDataPackageAndChannel) {
        final SuplaDataPacket suplaDataPacket = suplaDataPackageAndChannel.getSuplaDataPacket();
        final long callType = suplaDataPacket.callType;
        final CallType parse = callTypeParser.parse(callType).orElseThrow(() ->
                                                                                  new RuntimeException(format("There is not call type with number %s!", callType)));

        final ProtoWithSize decode = decoderFactory.getDecoder(parse).decode(suplaDataPacket.data);
        return new FromServerProtoDataAndChannel((FromServerProto) decode,
                                                        suplaDataPacketChannelToFromServerProtoChannel.apply(
                                                                suplaDataPackageAndChannel.getChannel()));
    }
}
