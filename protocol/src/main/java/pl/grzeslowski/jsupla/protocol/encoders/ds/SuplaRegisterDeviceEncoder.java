package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDevice;

import static java.util.Objects.requireNonNull;

@Deprecated
public class SuplaRegisterDeviceEncoder implements DeviceServerEncoder<SuplaRegisterDevice> {
    private final SuplaDeviceChannelEncoder deviceChannelEncoder;

    public SuplaRegisterDeviceEncoder(SuplaDeviceChannelEncoder deviceChannelEncoder) {
        this.deviceChannelEncoder = requireNonNull(deviceChannelEncoder);
    }

    @Override
    public byte[] encode(SuplaRegisterDevice proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.locationPwd, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.name, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.channelCount, data, offset);
        for (SuplaDeviceChannel channel : proto.channels) {
            final byte[] channelBytes = deviceChannelEncoder.encode(channel);
            offset += PrimitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
