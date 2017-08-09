package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.proto.structs.ds.DeviceServer;

public interface Decoder<T extends DeviceServer> {
    T decode(TSuplaDataPacket dataPacket);
}
