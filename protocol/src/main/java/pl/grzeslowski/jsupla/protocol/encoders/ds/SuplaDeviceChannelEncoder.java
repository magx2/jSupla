package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannel;

@Deprecated
public final class SuplaDeviceChannelEncoder implements Encoder<SuplaDeviceChannel> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaDeviceChannel proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeUnsignedByte(proto.number, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.type, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
