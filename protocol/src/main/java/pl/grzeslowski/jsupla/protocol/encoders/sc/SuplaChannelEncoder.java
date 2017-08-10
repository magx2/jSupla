package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;

import static java.util.Objects.requireNonNull;

public class SuplaChannelEncoder implements ServerClientEncoder<SuplaChannel> {
    private final SuplaChannelValueEncoder channelValueEncoder;

    public SuplaChannelEncoder(SuplaChannelValueEncoder channelValueEncoder) {
        this.channelValueEncoder = requireNonNull(channelValueEncoder);
    }

    @Override
    public byte[] encode(SuplaChannel proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.eol, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.id, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.func, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.online, data, offset);
        final byte[] value = channelValueEncoder.encode(proto.value);
        PrimitiveEncoder.writeBytes(value, data, offset);

        return data;
    }
}
