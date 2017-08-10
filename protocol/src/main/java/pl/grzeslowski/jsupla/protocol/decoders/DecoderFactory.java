package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.CallType;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoderForCallType(CallType callType);
}
