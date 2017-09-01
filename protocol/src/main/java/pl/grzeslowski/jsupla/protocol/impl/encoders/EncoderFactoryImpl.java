package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaDeviceChannelBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaRegisterDeviceBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaSetActivityTimeoutResultEncoderImpl;

import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

public class EncoderFactoryImpl implements EncoderFactory {
    private final PrimitiveEncoder primitiveEncoder = INSTANCE;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final T proto) {
        if (proto instanceof SuplaRegisterDeviceB) {
            final SuplaDeviceChannelBEncoder deviceChannelBEncoder =
                    new SuplaDeviceChannelBEncoderImpl(primitiveEncoder);
            return (Encoder<T>) new SuplaRegisterDeviceBEncoderImpl(primitiveEncoder, deviceChannelBEncoder);
        } else if (proto instanceof SuplaSetActivityTimeoutResult) {
            return (Encoder<T>) new SuplaSetActivityTimeoutResultEncoderImpl(primitiveEncoder);
        }

        throw new UnsupportedOperationException("EncoderFactoryImpl.getEncoder(proto)");
    }
}
