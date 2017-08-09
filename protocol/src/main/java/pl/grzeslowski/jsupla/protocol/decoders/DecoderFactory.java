package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

public interface DecoderFactory {
    <T extends DeviceServer> Decoder<T> getDecoderForCallType(CallType callType);
}
