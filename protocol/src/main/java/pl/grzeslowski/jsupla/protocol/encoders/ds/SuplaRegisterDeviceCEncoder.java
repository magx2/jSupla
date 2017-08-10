package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceC;

import static java.util.Objects.requireNonNull;

public class SuplaRegisterDeviceCEncoder implements DeviceServerEncoder<SuplaRegisterDeviceC> {
    private final SuplaDeviceChannelBEncoder deviceChannelBEncoder;

    public SuplaRegisterDeviceCEncoder(SuplaDeviceChannelBEncoder deviceChannelBEncoder) {
        this.deviceChannelBEncoder = requireNonNull(deviceChannelBEncoder);
    }

    @Override
    public byte[] encode(SuplaRegisterDeviceC proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.locationPwd, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.name, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.serverName, data, offset);
        for (SuplaDeviceChannelB channel : proto.channels) {
            final byte[] channelBytes = deviceChannelBEncoder.encode(channel);
            offset += PrimitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
