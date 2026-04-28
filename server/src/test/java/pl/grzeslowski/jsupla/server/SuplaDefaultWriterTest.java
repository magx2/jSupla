package pl.grzeslowski.jsupla.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

class SuplaDefaultWriterTest {
    private static final TestProto PROTO = new TestProto(() -> 123, 3);
    private final ChannelHandlerContext context = mock(ChannelHandlerContext.class);
    private final SuplaDefaultWriter writer =
            new SuplaDefaultWriter("test-uuid", new TestEncoderFactory(), context);

    @Test
    void writeShouldReturnFutureWithPacketMsgId() {
        var channel = new EmbeddedChannel();
        var channelFuture = new DefaultChannelPromise(channel);
        when(context.writeAndFlush(any(SuplaDataPacket.class))).thenReturn(channelFuture);

        var result = writer.write(PROTO);

        var packetCaptor = ArgumentCaptor.forClass(SuplaDataPacket.class);
        verify(context).writeAndFlush(packetCaptor.capture());
        assertThat(result.msgId()).isEqualTo(1L);
        assertThat(result.channel()).isSameAs(channel);
        assertThat(packetCaptor.getValue().rrId()).isEqualTo(result.msgId());
    }

    @Test
    void writeShouldIncrementReturnedMsgId() {
        when(context.writeAndFlush(any(SuplaDataPacket.class)))
                .thenReturn(mock(ChannelFuture.class), mock(ChannelFuture.class));

        var first = writer.write(PROTO);
        var second = writer.write(PROTO);

        assertThat(first.msgId()).isEqualTo(1L);
        assertThat(second.msgId()).isEqualTo(2L);
    }

    private record TestProto(CallType callType, int protoSize) implements FromServerProto {}

    private static final class TestEncoderFactory implements EncoderFactory {
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ProtoWithSize> Encoder<T> getEncoder(Class<T> proto) {
            return (Encoder<T>) TestEncoder.INSTANCE;
        }
    }

    private enum TestEncoder implements Encoder<TestProto> {
        INSTANCE;

        @Override
        public int encode(TestProto proto, byte[] bytes, int offset) {
            bytes[offset] = 1;
            bytes[offset + 1] = 2;
            bytes[offset + 2] = 3;
            return proto.protoSize();
        }
    }
}
