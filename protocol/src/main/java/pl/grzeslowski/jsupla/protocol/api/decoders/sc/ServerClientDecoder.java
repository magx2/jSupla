package pl.grzeslowski.jsupla.protocol.api.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;

public interface ServerClientDecoder<T extends ServerClient> extends ProtoWithSizeDecoder<T> {}
