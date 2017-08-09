package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.decoders.ds.TDS_SuplaDeviceChannel_BDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.ds.TDS_SuplaRegisterDevice_BDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_B;

public class DecoderFactoryImpl implements DecoderFactory {
    private final TDS_SuplaDeviceChannel_BDecoder channelDecoder = new TDS_SuplaDeviceChannel_BDecoder();
    private final TDS_SuplaRegisterDevice_BDecoder registerDeviceDecoder =
            new TDS_SuplaRegisterDevice_BDecoder(channelDecoder);

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DeviceServer> Decoder<T> getDecoderForCallType(CallType callType) {
        final int value = callType.getValue();
        if (value == SUPLA_DS_CALL_REGISTER_DEVICE_B.getValue()) {
            return (Decoder<T>) registerDeviceDecoder;
        }
        throw new IllegalArgumentException(format("Don't know decoder for call type %s", callType));
    }
}
