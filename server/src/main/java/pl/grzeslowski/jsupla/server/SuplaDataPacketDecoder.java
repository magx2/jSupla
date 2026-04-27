package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.server.NettyServer.NOISY_CALL_TYPE_IDS;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolPacketDeserializer;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

final class SuplaDataPacketDecoder extends ByteToMessageDecoder {
    public static final int SUPLA_DATA_PACKET_MIN_SIZE = ProtocolPacketDeserializer.MIN_FRAME_SIZE;
    private static final Logger LOGGER = LoggerFactory.getLogger(SuplaDataPacketDecoder.class);
    private final ProtocolPacketDeserializer packetDeserializer;
    private final String uuid;

    public SuplaDataPacketDecoder(String uuid) {
        this(uuid, ProtocolPacketDeserializer.INSTANCE);
    }

    SuplaDataPacketDecoder(String uuid, ProtocolPacketDeserializer packetDeserializer) {
        this.uuid = uuid;
        this.packetDeserializer = packetDeserializer;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < ProtocolPacketDeserializer.HEADER_SIZE) {
            return;
        }

        byte[] header = new byte[ProtocolPacketDeserializer.HEADER_SIZE];
        in.getBytes(in.readerIndex(), header);

        final long frameSize;
        try {
            frameSize = packetDeserializer.frameSize(header);
        } catch (ProtocolCodecException exception) {
            throw new CorruptedFrameException(exception);
        }

        if (in.readableBytes() < frameSize) {
            return;
        }

        byte[] frame = new byte[Math.toIntExact(frameSize)];
        in.readBytes(frame);
        final SuplaDataPacket suplaDataPacket;
        try {
            suplaDataPacket = packetDeserializer.deserialize(frame);
        } catch (ProtocolCodecException exception) {
            throw new CorruptedFrameException(exception);
        }

        if (NOISY_CALL_TYPE_IDS.contains(suplaDataPacket.callId())) {
            // log pings in trace
            LOGGER.trace(
                    "[{}] SuplaDataPacketDecoder.decode {} (SUPLA_SDC_CALL_PING_SERVER_RESULT)",
                    uuid,
                    suplaDataPacket);
        } else {
            LOGGER.debug("[{}] SuplaDataPacketDecoder.decode {}", uuid, suplaDataPacket);
        }
        out.add(suplaDataPacket);
    }
}
