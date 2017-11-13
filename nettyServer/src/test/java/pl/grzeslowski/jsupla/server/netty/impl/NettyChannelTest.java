package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.reactivestreams.Publisher;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;
import pl.grzeslowski.jsupla.protocoljava.api.types.FromClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.FromServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToServerEntity;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class NettyChannelTest {
    @Mock ChannelHandlerContext channelHandlerContext;
    @Mock CallTypeParser callTypeParser;
    @Mock DecoderFactory decoderFactory;
    @Mock Parser<Entity, Proto> parser;

    @Mock Decoder<ServerClient> decoder;

    @Before
    public void setUp() throws Exception {
        given(channelHandlerContext.write(any())).willReturn(mock(ChannelFuture.class));
        given(channelHandlerContext.flush()).willReturn(channelHandlerContext);
    }

    @Test
    public void shouldGetEntityInPipe() throws Exception {

        // given
        final SuplaDataPacket suplaDataPacket = RANDOM_SUPLA.nextObject(SuplaDataPacket.class);
        final ToServerEntity entity = mock(ToServerEntity.class);
        mockForSuplaDataPacket(suplaDataPacket, entity);

        final Flux<SuplaDataPacket> messagePipe = Flux.just(suplaDataPacket);

        // when
        final NettyChannel channel = new NettyChannel(channelHandlerContext, messagePipe, callTypeParser,
                                                             decoderFactory, parser);
        // then
        StepVerifier.create(channel.getMessagePipe())
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    public void shouldGetOnlyEntitiesThatHasKnownCallType() throws Exception {

        // given
        final SuplaDataPacket suplaDataPacket = RANDOM_SUPLA.nextObject(SuplaDataPacket.class);
        final SuplaDataPacket suplaDataPacketUnknownCallType = RANDOM_SUPLA.nextObject(SuplaDataPacket.class);

        final CallType callType = mock(CallType.class);
        given(callTypeParser.parse(suplaDataPacket.callType)).willReturn(Optional.of(callType));
        given(callTypeParser.parse(suplaDataPacketUnknownCallType.callType)).willReturn(Optional.empty());

        final @NotNull ServerClient proto = mock(ServerClient.class);
        given(decoder.decode(suplaDataPacket.data)).willReturn(proto);
        given(decoderFactory.<ServerClient>getDecoder(callType)).willReturn(decoder);

        final ToServerEntity entity = mock(ToServerEntity.class);
        given(parser.parse(proto)).willReturn(entity);

        final Flux<SuplaDataPacket> messagePipe = Flux.just(suplaDataPacketUnknownCallType, suplaDataPacket);

        // when
        final NettyChannel channel = new NettyChannel(channelHandlerContext, messagePipe, callTypeParser,
                                                             decoderFactory, parser);
        // then
        StepVerifier.create(channel.getMessagePipe())
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    public void shouldGetOnlyEntitiesThatHas() throws Exception {

        // given
        final SuplaDataPacket suplaDataPacket = RANDOM_SUPLA.nextObject(SuplaDataPacket.class);
        final ToServerEntity entity = mock(ToServerEntity.class);
        mockForSuplaDataPacket(suplaDataPacket, entity);

        final SuplaDataPacket suplaDataPacketWrong = RANDOM_SUPLA.nextObject(SuplaDataPacket.class);
        final FromClientEntity entityWrong = mock(FromClientEntity.class);
        mockForSuplaDataPacket(suplaDataPacketWrong, entityWrong);

        final Flux<SuplaDataPacket> messagePipe = Flux.just(suplaDataPacket, suplaDataPacketWrong);

        // when
        final NettyChannel channel = new NettyChannel(channelHandlerContext, messagePipe, callTypeParser,
                                                             decoderFactory, parser);
        // then
        StepVerifier.create(channel.getMessagePipe())
                .expectNext(entity)
                .verifyComplete();
    }

    private void mockForSuplaDataPacket(final SuplaDataPacket suplaDataPacket, final Entity entity) {
        final CallType callType = mock(CallType.class);
        given(callTypeParser.parse(suplaDataPacket.callType)).willReturn(Optional.of(callType));

        final @NotNull ServerClient proto = mock(ServerClient.class);
        given(decoder.decode(suplaDataPacket.data)).willReturn(proto);
        given(decoderFactory.<ServerClient>getDecoder(callType)).willReturn(decoder);

        given(parser.parse(proto)).willReturn(entity);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelHandlerContextIsNull() {
        new NettyChannel(null, Flux.empty(), callTypeParser, decoderFactory, parser);
    }

    @Test
    public void shouldCloseChannelHandlerContext() throws Exception {

        // given
        final NettyChannel channel = new NettyChannel(channelHandlerContext, Flux.empty(), callTypeParser,
                                                             decoderFactory, parser);

        // when
        channel.close();

        // then
        verify(channelHandlerContext).close();
    }

    @Test
    public void shouldBufferOnSize() throws Exception {

        // given
        final Duration timespan = Duration.ofHours(1);
        final int bufferMaxSize = 10;
        final int batchesToRequest = 2;
        NettyChannel.BufferParams bufferParams = new NettyChannel.BufferParams(timespan, bufferMaxSize);
        final NettyChannel channel = new NettyChannel(channelHandlerContext, Flux.empty(), callTypeParser,
                                                             decoderFactory, parser, bufferParams);

        final Stream<FromServerEntity> entities = Stream.generate(() -> mock(FromServerEntity.class));
        Supplier<Publisher<? extends LocalDateTime>> publisherSupplier = () -> {
            final Flux<FromServerEntity> flux = Flux.fromStream(entities);

            // when
            return channel.write(flux);
        };

        // then
        StepVerifier.withVirtualTime(publisherSupplier)
                .expectSubscription()
                .thenRequest(batchesToRequest)
                .expectNextCount(batchesToRequest)
                .thenCancel()
                .verify();
        verify(channelHandlerContext, times(batchesToRequest * bufferMaxSize)).write(any(FromServerEntity.class));
        verify(channelHandlerContext, times(batchesToRequest)).flush();
    }

    @Test
    public void shouldBufferOnTime() throws Exception {

        // given
        final Duration timespan = Duration.ofHours(1);
        final int bufferMaxSize = 10;
        final int elements = bufferMaxSize / 2;
        NettyChannel.BufferParams bufferParams = new NettyChannel.BufferParams(timespan, bufferMaxSize);
        final NettyChannel channel = new NettyChannel(channelHandlerContext, Flux.empty(), callTypeParser,
                                                             decoderFactory, parser, bufferParams);

        final Stream<FromServerEntity> entities = Stream.generate(() -> mock(FromServerEntity.class)).limit(elements);

        Supplier<Publisher<? extends LocalDateTime>> publisherSupplier = () -> {
            final Flux<FromServerEntity> entitiesFlux = Flux.fromStream(entities);
            Flux<FromServerEntity> never = Flux.never();

            // when
            return channel.write(Flux.merge(entitiesFlux, never));
        };

        // then
        StepVerifier.withVirtualTime(publisherSupplier)
                .expectSubscription()
                .thenRequest(1)
                .thenAwait(timespan)
                .expectNextCount(1)
                .thenCancel()
                .verify();
        verify(channelHandlerContext, times(elements)).write(any(FromServerEntity.class));
        verify(channelHandlerContext).flush();
    }

    @Test
    public void shouldBufferOnSizeAndTime() throws Exception {

        // given
        final Duration timespan = Duration.ofHours(1);
        final int bufferMaxSize = 10;
        final int elements = bufferMaxSize * 2 + bufferMaxSize / 2;
        final int batchesToRequest = 3;
        NettyChannel.BufferParams bufferParams = new NettyChannel.BufferParams(timespan, bufferMaxSize);
        final NettyChannel channel = new NettyChannel(channelHandlerContext, Flux.empty(), callTypeParser,
                                                             decoderFactory, parser, bufferParams);

        final Stream<FromServerEntity> entities = Stream.generate(() -> mock(FromServerEntity.class)).limit(elements);

        Supplier<Publisher<? extends LocalDateTime>> publisherSupplier = () -> {
            final Flux<FromServerEntity> flux = Flux.fromStream(entities);

            // when
            return channel.write(flux);
        };

        // then
        StepVerifier.withVirtualTime(publisherSupplier)
                .expectSubscription()
                .expectNextCount(batchesToRequest)
                .expectComplete()
                .verify();
        verify(channelHandlerContext, times(elements)).write(any(FromServerEntity.class));
        verify(channelHandlerContext, times(batchesToRequest)).flush();
    }
}