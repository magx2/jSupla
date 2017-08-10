package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.ServerClient;

public interface ServerClientEncoder<T extends ServerClient> extends SuplaDataPacketEncoder<T> {
}
