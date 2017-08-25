package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.dcs.SuplaSetActivityTimeoutDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceBDecoderImpl;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_B;

public class DecoderFactoryImpl implements DecoderFactory {
    private final SuplaDeviceChannelBDecoderImpl channelDecoder =
            new SuplaDeviceChannelBDecoderImpl(PrimitiveDecoderImpl.INSTANCE);
    private final SuplaRegisterDeviceBDecoderImpl registerDeviceDecoder =
            new SuplaRegisterDeviceBDecoderImpl(PrimitiveDecoderImpl.INSTANCE, channelDecoder);

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> ProtoWithSizeDecoder<T> getDecoderForCallType(CallType callType) {
        final int value = callType.getValue();
        if (value == SUPLA_DS_CALL_REGISTER_DEVICE_B.getValue()) {
            return (ProtoWithSizeDecoder<T>) registerDeviceDecoder;
        } else if (value == SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT.getValue()) {
            return (ProtoWithSizeDecoder<T>) new SuplaSetActivityTimeoutDecoderImpl(PrimitiveDecoderImpl.INSTANCE);
        }

        throw new IllegalArgumentException(format("Don't know decoder for call type %s", callType));
    }
}
