package pl.grzeslowski.jsupla.protocol.api.decoders.dcs;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;

public interface DeviceClientServerDecoder<T extends DeviceClientServer> extends Decoder<T> {
}
