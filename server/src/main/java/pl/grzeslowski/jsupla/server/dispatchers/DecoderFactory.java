package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.types.ProtoWithSize;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoderForCallType(CallType callType);
}
