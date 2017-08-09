package pl.grzeslowski.jsupla.protocol.decoders.scd;

import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.structs.scd.ServerClientDevice;

public interface ServerClientDeviceDecoder<T extends ServerClientDevice> extends Decoder<T> {
}
