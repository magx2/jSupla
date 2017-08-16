package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDevice;

import static java.util.Objects.requireNonNull;

@Deprecated
public final class SuplaRegisterDeviceEncoder implements DeviceServerEncoder<SuplaRegisterDevice> {
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaDeviceChannelEncoder deviceChannelEncoder;

    public SuplaRegisterDeviceEncoder(PrimitiveEncoder primitiveEncoder,
                                      SuplaDeviceChannelEncoder deviceChannelEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.deviceChannelEncoder = requireNonNull(deviceChannelEncoder);
    }

    @Override
    public byte[] encode(SuplaRegisterDevice proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += primitiveEncoder.writeBytes(proto.locationPwd, data, offset);
        offset += primitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += primitiveEncoder.writeBytes(proto.name, data, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.channelCount, data, offset);
        for (SuplaDeviceChannel channel : proto.channels) {
            final byte[] channelBytes = deviceChannelEncoder.encode(channel);
            offset += primitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
