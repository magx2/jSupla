package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.ds.SuplaRegisterDeviceBDecoder;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_B;

public class DecoderFactoryImpl implements DecoderFactory {
    private final SuplaDeviceChannelBDecoder channelDecoder = new SuplaDeviceChannelBDecoder();
    private final SuplaRegisterDeviceBDecoder registerDeviceDecoder =
            new SuplaRegisterDeviceBDecoder(channelDecoder);

    @SuppressWarnings("unchecked")
    @Override
    public <T extends PackableProto> Decoder<T> getDecoderForCallType(CallType callType) {
        final int value = callType.getValue();
        if (value == SUPLA_DS_CALL_REGISTER_DEVICE_B.getValue()) {
            return (Decoder<T>) registerDeviceDecoder;
        }
        throw new IllegalArgumentException(format("Don't know decoder for call type %s", callType));
    }
}
