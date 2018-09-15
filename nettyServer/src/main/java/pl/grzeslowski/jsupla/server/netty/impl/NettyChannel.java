package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.channel.ChannelHandlerContext;
import org.javatuples.Pair;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;
import pl.grzeslowski.jsupla.protocoljava.api.types.FromServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToServerEntity;
import pl.grzeslowski.jsupla.server.api.Channel;
import pl.grzeslowski.jsupla.server.api.ChannelDescription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;

public final class NettyChannel implements Channel {
    private final ChannelHandlerContext channelHandlerContext;
    private final EncoderFactory encoderFactory;
    private final Serializer<Entity, ProtoWithCallType> serializer;
    private final BufferParams bufferParams;
    private final Flux<ToServerEntity> messagePipe;
    private final AtomicLong msgId = new AtomicLong(1);

    NettyChannel(final ChannelHandlerContext channelHandlerContext,
                 final Flux<SuplaDataPacket> messagePipe,
                 final CallTypeParser callTypeParser,
                 final DecoderFactory decoderFactory,
                 final EncoderFactory encoderFactory,
                 final Parser<Entity, Proto> parser,
                 final Serializer<Entity, ProtoWithCallType> serializer,
                 final BufferParams bufferParams) {
        this.channelHandlerContext = requireNonNull(channelHandlerContext);
        this.encoderFactory = requireNonNull(encoderFactory);
        this.serializer = requireNonNull(serializer);
        this.bufferParams = requireNonNull(bufferParams);
        //noinspection ConstantConditions
        this.messagePipe = messagePipe
                                   .map(suplaDataPacket -> callTypePair(callTypeParser, suplaDataPacket))
                                   .filter(pair -> pair.getValue1().isPresent())
                                   .map(pair -> Pair.with(pair.getValue0(), pair.getValue1().get()))
                                   .map(pair -> decoderPair(decoderFactory, pair))
                                   .map(pair -> pair.getValue1().decode(pair.getValue0().data))
                                   .map(parser::parse)
                                   .filter(entity -> ToServerEntity.class.isAssignableFrom(entity.getClass()))
                                   .cast(ToServerEntity.class);
    }

    NettyChannel(final ChannelHandlerContext channelHandlerContext,
                 final Flux<SuplaDataPacket> messagePipe,
                 final CallTypeParser callTypeParser,
                 final DecoderFactory decoderFactory,
                 final EncoderFactory encoderFactory,
                 final Parser<Entity, Proto> parser,
                 final Serializer<Entity, ProtoWithCallType> serializer) {
        this(channelHandlerContext,
                messagePipe,
                callTypeParser,
                decoderFactory,
                encoderFactory,
                parser,
                serializer,
                BufferParams.DEFAULT);
    }

    private static Pair<SuplaDataPacket, Optional<CallType>> callTypePair(final CallTypeParser callTypeParser,
                                                                          final SuplaDataPacket suplaDataPacket) {
        return Pair.with(suplaDataPacket, callTypeParser.parse(suplaDataPacket.callType));
    }

    private static Pair<SuplaDataPacket,
                               Decoder<? extends ProtoWithSize>> decoderPair(DecoderFactory decoderFactory,
                                                                             Pair<SuplaDataPacket, CallType> pair) {
        return Pair.with(pair.getValue0(), decoderFactory.getDecoder(pair.getValue1()));
    }

    @Override
    public Flux<ToServerEntity> getMessagePipe() {
        return messagePipe;
    }

    @Override
    public Flux<LocalDateTime> write(final Flux<FromServerEntity> fromServerEntityFlux) {
        return fromServerEntityFlux
                       .map(serializer::serialize)
                       .filter(proto -> proto instanceof ProtoToSend)
                       .cast(ProtoToSend.class)
                       .map(this::encodeProto)
                       .map(channelHandlerContext::write)
                       .bufferTimeout(bufferParams.bufferMaxSize, bufferParams.timespan)
                       .map(__ -> channelHandlerContext.flush())
                       .map(__ -> LocalDateTime.now());
    }

    private SuplaDataPacket encodeProto(ProtoToSend proto) {
        final Encoder<ProtoToSend> encoder = encoderFactory.getEncoder(proto);
        return new SuplaDataPacket(
                (short) 5,
                msgId.getAndIncrement(),
                proto.callType().getValue(),
                encoder.encode(proto));
    }
    
    @Override
    public ChannelDescription getChannelDescription() {
        throw new UnsupportedOperationException("NettyChannel.getChannelDescription()");
    }

    @Override
    public void close() {
        channelHandlerContext.close();
    }

    static class BufferParams {
        static final int DEFAULT_BUFFER_MAX_SIZE = 5;
        static final Duration DEFAULT_TIMESPAN = Duration.ofSeconds(1L);
        private static final BufferParams DEFAULT = new BufferParams();

        private final Duration timespan;
        private final int bufferMaxSize;

        private BufferParams() {
            this(DEFAULT_TIMESPAN, DEFAULT_BUFFER_MAX_SIZE);
        }

        BufferParams(final Duration timespan, final int bufferMaxSize) {
            this.timespan = timespan;
            this.bufferMaxSize = bufferMaxSize;
        }

        @Override
        public String toString() {
            return "BufferParams{" +
                           "timespan=" + timespan +
                           ", bufferMaxSize=" + bufferMaxSize +
                           '}';
        }
    }
}
