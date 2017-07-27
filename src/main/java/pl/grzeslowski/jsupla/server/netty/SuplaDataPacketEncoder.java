package pl.grzeslowski.jsupla.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_TAG;

@ChannelHandler.Sharable
final class SuplaDataPacketEncoder extends MessageToByteEncoder<TSuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaDataPacketEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, TSuplaDataPacket msg, ByteBuf out) throws Exception {
        logger.trace("Encoding {}", msg);
        out.writeBytes(SUPLA_TAG)
                .writeByte((byte) msg.version)
                .writeIntLE((int) msg.rrId)
                .writeIntLE((int) msg.callType)
                .writeIntLE((int) msg.dataSize)
                .writeBytes(msg.data);
    }
}
