package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.javatuples.Pair;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.traits.RegisterDeviceTrait;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.api.Channel;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

@Slf4j
public final class NettyChannel implements Channel {
    private final ChannelHandlerContext channelHandlerContext;
    private final EncoderFactory encoderFactory;
    private final BufferParams bufferParams;
    private final Flux<ToServerProto> messagePipe;
    private final AtomicLong msgId = new AtomicLong(1);

    NettyChannel(ChannelHandlerContext channelHandlerContext,
                 Flux<SuplaDataPacket> messagePipe,
                 CallTypeParser callTypeParser,
                 DecoderFactory decoderFactory,
                 EncoderFactory encoderFactory,
                 ChannelTypeMapper typeMapper,
                 BufferParams bufferParams) {
        this.channelHandlerContext = requireNonNull(channelHandlerContext);
        this.encoderFactory = requireNonNull(encoderFactory);
        this.bufferParams = requireNonNull(bufferParams);
        this.messagePipe = messagePipe
            .map(suplaDataPacket -> Pair.with(suplaDataPacket, callTypeParser.parse(suplaDataPacket.callId)))
            .filter(pair -> pair.getValue1().isPresent())
            .map(pair -> {
                val suplaDataPacket = pair.getValue0();
                // optional checked in filter
                @SuppressWarnings("OptionalGetWithoutIsPresent")
                val callType = pair.getValue1().get();
                Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
                val data = suplaDataPacket.data;
                log.trace("Decoding data with decoder {}:\n{}", decoder.getClass().getName(), data);
                return decoder.decode(data);
            })
            .filter(entity -> ToServerProto.class.isAssignableFrom(entity.getClass()))
            .cast(ToServerProto.class);

        this.messagePipe
            .filter(entity -> RegisterDeviceTrait.class.isAssignableFrom(entity.getClass()))
            .cast(RegisterDeviceTrait.class)
            .subscribe(typeMapper::registerDevice);
    }

    public NettyChannel(ChannelHandlerContext ctx, Flux<SuplaDataPacket> flux, CallTypeParser callTypeParser, DecoderFactory decoderFactory, EncoderFactory encoderFactory) {
        this(ctx, flux, callTypeParser, decoderFactory, encoderFactory, new ChannelTypeMapper(), BufferParams.DEFAULT);
    }

    private static Pair<SuplaDataPacket,
        Decoder<? extends ProtoWithSize>> decoderPair(DecoderFactory decoderFactory,
                                                      Pair<SuplaDataPacket, CallType> pair) {
        return Pair.with(pair.getValue0(), decoderFactory.getDecoder(pair.getValue1()));
    }

    @Override
    public Flux<ToServerProto> getMessagePipe() {
        return messagePipe;
    }

    @Override
    public Flux<LocalDateTime> write(final Flux<FromServerProto> fromServerEntityFlux) {
        return fromServerEntityFlux
            .map(this::encodeProto)
            .map(channelHandlerContext::write)
            .bufferTimeout(bufferParams.bufferMaxSize, bufferParams.timespan)
            .map(__ -> channelHandlerContext.flush())
            .map(__ -> LocalDateTime.now());
    }

    private SuplaDataPacket encodeProto(ProtoToSend proto) {
        final Encoder<ProtoToSend> encoder = encoderFactory.getEncoder(proto);
        byte[] encode = encoder.encode(proto);
        return new SuplaDataPacket(
            SUPLA_TAG,
            (short) 5,
            msgId.getAndIncrement(),
            proto.callType().getValue(),
            encode.length,
            encode);
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
