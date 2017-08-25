package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoder(T proto);

    <T extends ProtoWithSize & ProtoWithCallType> Decoder<T> getDecoder(CallType callType);
}
