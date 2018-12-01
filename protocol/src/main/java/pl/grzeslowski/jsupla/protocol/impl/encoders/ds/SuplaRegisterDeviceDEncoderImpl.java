package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceDEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterDeviceDEncoderImpl implements SuplaRegisterDeviceDEncoder {
    public static final SuplaRegisterDeviceDEncoderImpl INSTANCE =
            new SuplaRegisterDeviceDEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaDeviceChannelBEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaDeviceChannelBEncoder suplaDeviceChannelBEncoder;

    SuplaRegisterDeviceDEncoderImpl(final PrimitiveEncoder primitiveEncoder,
                                    final SuplaDeviceChannelBEncoder suplaDeviceChannelBEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.suplaDeviceChannelBEncoder = requireNonNull(suplaDeviceChannelBEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaRegisterDeviceD proto) {
        final byte[] bytes = new byte[proto.size()];

        int offset = 0;
        offset += primitiveEncoder.writeBytes(proto.email, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.authKey, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.guid, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.name, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.serverName, bytes, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.channelCount, bytes, offset);
        for (SuplaDeviceChannelB channel : proto.channels) {
            final byte[] channelBytes = suplaDeviceChannelBEncoder.encode(channel);
            offset += primitiveEncoder.writeBytes(channelBytes, bytes, offset);
        }

        return bytes;
    }
}
