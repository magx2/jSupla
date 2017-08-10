package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.ClientServer;

public interface ClientServerEncoder<T extends ClientServer> extends SuplaDataPacketEncoder<T> {
}
