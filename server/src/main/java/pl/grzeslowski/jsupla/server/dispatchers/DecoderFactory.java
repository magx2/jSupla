package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.decoders.Decoder;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoderForCallType(CallType callType);
}
