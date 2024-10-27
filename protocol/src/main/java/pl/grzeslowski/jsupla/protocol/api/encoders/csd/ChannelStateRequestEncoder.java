package pl.grzeslowski.jsupla.protocol.api.encoders.csd;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.csd.ChannelStateRequest;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

public class ChannelStateRequestEncoder implements pl.grzeslowski.jsupla.protocol.api.encoders.csd.ClientServerDeviceEncoder<ChannelStateRequest> {
    public static final ChannelStateRequestEncoder INSTANCE = new ChannelStateRequestEncoder();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(ChannelStateRequest proto) {
        val bytes = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.INSTANCE.writeInt(proto.senderId, bytes, offset);
        if (proto.channelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(proto.channelId, bytes, offset);
        } else if (proto.channelNumber != null) {
            PrimitiveEncoder.INSTANCE.writeShort(proto.channelNumber, bytes, offset);
        } else {
            throw new IllegalArgumentException("Wrong channelId|channelNumber union: " + proto);
        }
        offset += INT_SIZE;

        return bytes;
    }
}