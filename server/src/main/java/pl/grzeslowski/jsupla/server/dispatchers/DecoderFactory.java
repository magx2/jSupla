package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface DecoderFactory {
    <T extends ProtoWithSize> ProtoWithSizeDecoder<T> getDecoderForCallType(CallType callType);
}
