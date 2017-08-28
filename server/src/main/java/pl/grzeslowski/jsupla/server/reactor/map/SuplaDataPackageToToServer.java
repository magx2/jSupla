package pl.grzeslowski.jsupla.server.reactor.map;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class SuplaDataPackageToToServer implements Function<SuplaDataPacket, ToServerProto> {
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;

    public SuplaDataPackageToToServer(final CallTypeParser callTypeParser, final DecoderFactory decoderFactory) {
        this.callTypeParser = callTypeParser;
        this.decoderFactory = requireNonNull(decoderFactory);
    }

    @Override
    public ToServerProto apply(final SuplaDataPacket suplaDataPacket) {
        // @formatter:off
        final CallType callType = callTypeParser.parse(suplaDataPacket.callType).orElseThrow(
            () -> new IllegalArgumentException(format("Don't know this call type %s", suplaDataPacket.callType)));
        // @formatter:on

        final Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        final ProtoWithSize proto = decoder.decode(suplaDataPacket.data);
        if (proto instanceof ToServerProto) {
            return (ToServerProto) proto;
        } else {
            throw new IllegalStateException(
                                                   format("Can't cast %s to %s! Decoder that was used %s.",
                                                           proto.getClass().getSimpleName(),
                                                           ToServerProto.class.getSimpleName(),
                                                           decoder.getClass().getSimpleName()));
        }
    }

}
