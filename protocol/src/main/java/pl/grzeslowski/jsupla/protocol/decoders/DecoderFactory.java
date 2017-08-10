package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.calltypes.CallType;

public interface DecoderFactory {
    <T extends PackableProto> Decoder<T> getDecoderForCallType(CallType callType);
}
