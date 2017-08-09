package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.consts.CallType;
import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;

import static java.lang.String.format;

public class DecoderFactoryImpl implements DecoderFactory {
    private final TDS_SuplaDeviceChannel_BDecoder channelDecoder = new TDS_SuplaDeviceChannel_BDecoder();
    private final TDS_SuplaRegisterDevice_BDecoder registerDeviceDecoder = new TDS_SuplaRegisterDevice_BDecoder(channelDecoder);

    @SuppressWarnings("unchecked")
    @Override
    public <DS extends DeviceServer> Decoder<DS> getDecoderForCallType(CallType callType) {
        switch (callType) {
            case SUPLA_DS_CALL_REGISTER_DEVICE_B:
                return (Decoder<DS>) registerDeviceDecoder;
            default:
                throw new IllegalArgumentException(format("Don't know decoder for call type %s", callType.name()));
        }
    }
}
