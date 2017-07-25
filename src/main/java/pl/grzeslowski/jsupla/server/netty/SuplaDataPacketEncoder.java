package pl.grzeslowski.jsupla.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_TAG;

final class SuplaDataPacketEncoder extends MessageToByteEncoder<TSuplaDataPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TSuplaDataPacket msg, ByteBuf out) throws Exception {
        out.writeBytes(SUPLA_TAG)
                .writeByte((byte) msg.version)
                .writeIntLE((int) msg.rrId)
                .writeIntLE((int) msg.callType)
                .writeIntLE((int) msg.dataSize)
                .writeBytes(msg.data);
    }
}
