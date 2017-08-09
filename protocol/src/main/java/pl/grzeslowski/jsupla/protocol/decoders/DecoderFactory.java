package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.call_types.CallType;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

public interface DecoderFactory {
    <DS extends DeviceServer> Decoder<DS> getDecoderForCallType(CallType callType);
}
