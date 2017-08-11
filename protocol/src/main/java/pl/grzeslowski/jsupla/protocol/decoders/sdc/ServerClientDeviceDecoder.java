package pl.grzeslowski.jsupla.protocol.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.ServerDeviceClient;

public interface ServerClientDeviceDecoder<T extends ServerDeviceClient> extends Decoder<T> {
}
