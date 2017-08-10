package pl.grzeslowski.jsupla.protocol.encoders.sc;


import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannelValue;

import static java.util.Objects.requireNonNull;

public class SuplaChannelValueEncoder implements ServerClientEncoder<SuplaChannelValue> {
    private final pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder channelValueEncoder;

    public SuplaChannelValueEncoder(
            pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder channelValueEncoder) {
        this.channelValueEncoder = requireNonNull(channelValueEncoder);
    }

    @Override
    public byte[] encode(SuplaChannelValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.eol, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.id, data, offset);
        offset += PrimitiveEncoder.writeByte(proto.online, data, offset);
        final byte[] channelBytes = channelValueEncoder.encode(proto.value);
        PrimitiveEncoder.writeBytes(channelBytes, data, offset);

        return data;
    }
}
