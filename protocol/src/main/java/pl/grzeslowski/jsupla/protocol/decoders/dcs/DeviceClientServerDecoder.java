package pl.grzeslowski.jsupla.protocol.decoders.dcs;

import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.DeviceClientServer;

public interface DeviceClientServerDecoder<T extends DeviceClientServer> extends Decoder<T> {
}
