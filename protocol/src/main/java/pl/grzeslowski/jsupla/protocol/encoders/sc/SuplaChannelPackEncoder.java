package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannelPack;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelPackEncoder implements ServerClientEncoder<SuplaChannelPack> {
    private final SuplaChannelEncoder channelEncoder;

    public SuplaChannelPackEncoder(SuplaChannelEncoder channelEncoder) {
        this.channelEncoder = requireNonNull(channelEncoder);
    }

    @Override
    public byte[] encode(SuplaChannelPack proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.count, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.totalLeft, data, offset);
        for (SuplaChannel channel : proto.channels) {
            final byte[] channelBytes = channelEncoder.encode(channel);
            offset += PrimitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
