package pl.grzeslowski.jsupla.protocol.api.decoders.scs;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.scs.ServerClientServer;

public interface ServerClientServerDecoder<T extends ServerClientServer> extends ProtoWithSizeDecoder<T> {
}
