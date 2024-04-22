package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterDeviceEncoderImpl implements SuplaRegisterDeviceEncoder {
    public static final SuplaRegisterDeviceEncoderImpl INSTANCE =
        new SuplaRegisterDeviceEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaDeviceChannelEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaDeviceChannelEncoder deviceChannelEncoder;

    SuplaRegisterDeviceEncoderImpl(PrimitiveEncoder primitiveEncoder,
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
