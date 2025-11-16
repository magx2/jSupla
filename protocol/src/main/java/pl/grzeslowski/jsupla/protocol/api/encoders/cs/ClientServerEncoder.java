package pl.grzeslowski.jsupla.protocol.api.encoders.cs;

import pl.grzeslowski.jsupla.protocol.api.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;

public interface ClientServerEncoder<T extends ClientServer> extends ToSuplaDataPacketEncoder<T> {}
