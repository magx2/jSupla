package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;


import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sc.ServerClientEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannelValue;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelValueEncoderImpl implements ServerClientEncoder<SuplaChannelValue> {
    private final PrimitiveEncoder primitiveEncoder;
    private final pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder channelValueEncoder;

    public SuplaChannelValueEncoderImpl(
            PrimitiveEncoder primitiveEncoder,
            pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder channelValueEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.channelValueEncoder = requireNonNull(channelValueEncoder);
    }

    @Override
    public byte[] encode(SuplaChannelValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, data, offset);
        offset += primitiveEncoder.writeInteger(proto.id, data, offset);
        offset += primitiveEncoder.writeByte(proto.online, data, offset);
        final byte[] channelBytes = channelValueEncoder.encode(proto.value);
        primitiveEncoder.writeBytes(channelBytes, data, offset);

        return data;
    }
}
