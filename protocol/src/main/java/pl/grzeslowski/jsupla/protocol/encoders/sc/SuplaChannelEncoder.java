package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelEncoder implements ServerClientEncoder<SuplaChannel> {
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelValueEncoder channelValueEncoder;

    public SuplaChannelEncoder(PrimitiveEncoder primitiveEncoder, SuplaChannelValueEncoder channelValueEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.channelValueEncoder = requireNonNull(channelValueEncoder);
    }

    @Override
    public byte[] encode(SuplaChannel proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, data, offset);
        offset += primitiveEncoder.writeInteger(proto.id, data, offset);
        offset += primitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += primitiveEncoder.writeInteger(proto.func, data, offset);
        offset += primitiveEncoder.writeInteger(proto.online, data, offset);
        final byte[] value = channelValueEncoder.encode(proto.value);
        primitiveEncoder.writeBytes(value, data, offset);

        return data;
    }
}
