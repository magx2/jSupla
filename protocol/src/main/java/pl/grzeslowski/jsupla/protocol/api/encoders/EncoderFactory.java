package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface EncoderFactory {
    <T extends ProtoWithSize> Encoder<T> getEncoder(T proto);

    <T extends ProtoWithSize & ProtoWithCallType> Encoder<T> getEncoder(CallType callType);
}
