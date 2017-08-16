package pl.grzeslowski.jsupla.protocol.api.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;

public interface ServerClientEncoder<T extends ServerClient> extends ToSuplaDataPacketEncoder<T> {
}
