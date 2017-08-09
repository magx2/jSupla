package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

public interface Decoder<T extends DeviceServer> {
    T decode(TSuplaDataPacket dataPacket);
}
