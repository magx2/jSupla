package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelValue;

import static java.util.Objects.requireNonNull;

public final class SuplaDeviceChannelValueEncoder implements DeviceServerEncoder<SuplaDeviceChannelValue> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaDeviceChannelValueEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaDeviceChannelValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.channelNumber, data, offset);
        offset += primitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
