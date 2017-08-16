package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.ds.DeviceServerEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;

import static java.util.Objects.requireNonNull;

@Deprecated
public final class SuplaRegisterDeviceBEncoderImpl implements DeviceServerEncoder<SuplaRegisterDeviceB> {
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaDeviceChannelBEncoderImpl deviceChannelBEncoder;

    public SuplaRegisterDeviceBEncoderImpl(PrimitiveEncoder primitiveEncoder,
                                           SuplaDeviceChannelBEncoderImpl deviceChannelBEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.deviceChannelBEncoder = requireNonNull(deviceChannelBEncoder);
    }

    @Override
    public byte[] encode(SuplaRegisterDeviceB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += primitiveEncoder.writeBytes(proto.locationPwd, data, offset);
        offset += primitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += primitiveEncoder.writeBytes(proto.name, data, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.channelCount, data, offset);
        for (SuplaDeviceChannelB channel : proto.channels) {
            final byte[] channelBytes = deviceChannelBEncoder.encode(channel);
            offset += primitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
