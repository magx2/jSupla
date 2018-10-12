package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoder(Class<T> proto);

    @SuppressWarnings("unchecked")
    default <T extends ProtoWithSize> Decoder<T> getDecoder(T proto) {
        return (Decoder<T>) getDecoder(proto.getClass());
    }

    <T extends ProtoWithSize & ProtoWithCallType> Decoder<T> getDecoder(CallType callType);
}
