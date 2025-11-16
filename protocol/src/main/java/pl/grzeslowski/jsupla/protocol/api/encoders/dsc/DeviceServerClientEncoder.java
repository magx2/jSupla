package pl.grzeslowski.jsupla.protocol.api.encoders.dsc;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dsc.DeviceServerClient;

public interface DeviceServerClientEncoder<T extends DeviceServerClient> extends Encoder<T> {}
