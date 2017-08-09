package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.consts.CallType;
import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;

public interface DecoderFactory {
    <DS extends DeviceServer> Decoder<DS> getDecoderForCallType(CallType callType);
}
