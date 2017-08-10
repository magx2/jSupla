package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;

import static java.util.Objects.requireNonNull;

@Deprecated
public final class SuplaRegisterDeviceBEncoder implements DeviceServerEncoder<SuplaRegisterDeviceB> {
    private final SuplaDeviceChannelBEncoder deviceChannelBEncoder;

    public SuplaRegisterDeviceBEncoder(SuplaDeviceChannelBEncoder deviceChannelBEncoder) {
        this.deviceChannelBEncoder = requireNonNull(deviceChannelBEncoder);
    }

    @Override
    public byte[] encode(SuplaRegisterDeviceB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.locationPwd, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.name, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.channelCount, data, offset);
        for (SuplaDeviceChannelB channel : proto.channels) {
            final byte[] channelBytes = deviceChannelBEncoder.encode(channel);
            offset += PrimitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
