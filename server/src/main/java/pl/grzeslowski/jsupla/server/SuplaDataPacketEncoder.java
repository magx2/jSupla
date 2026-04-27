package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.server.NettyServer.NOISY_CALL_TYPE_IDS;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolPacketSerializer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

final class SuplaDataPacketEncoder extends MessageToByteEncoder<SuplaDataPacket> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuplaDataPacketEncoder.class);
    private final ProtocolPacketSerializer packetSerializer;
    private final String uuid;

    public SuplaDataPacketEncoder(String uuid) {
        this(uuid, ProtocolPacketSerializer.INSTANCE);
    }

    SuplaDataPacketEncoder(String uuid, ProtocolPacketSerializer packetSerializer) {
        this.uuid = uuid;
        this.packetSerializer = packetSerializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, SuplaDataPacket msg, ByteBuf out) {
        if (NOISY_CALL_TYPE_IDS.contains(msg.callId())) {
            // log pings in trace
            LOGGER.trace(
                    "[{}] SuplaDataPacketEncoder.encode(ctx, {}, out)"
                            + " (SUPLA_SDC_CALL_PING_SERVER_RESULT)",
                    uuid,
                    msg);
        } else {
            LOGGER.debug("[{}] SuplaDataPacketEncoder.encode(ctx, {}, out)", uuid, msg);
        }
        out.writeBytes(packetSerializer.serialize(msg));
    }
}
