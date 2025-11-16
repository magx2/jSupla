package pl.grzeslowski.jsupla.protocol.api.encoders.scs;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.scs.ServerClientServer;

public interface ServerClientServerEncoder<T extends ServerClientServer> extends Encoder<T> {}
