package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoderForCallType(CallType callType);
}
