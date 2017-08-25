package pl.grzeslowski.jsupla.protocol.api.decoders.cs;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;

public interface ClientServerDecoder<T extends ClientServer> extends ProtoWithSizeDecoder<T> {
}
