package pl.grzeslowski.jsupla.protocol.api.encoders.sds;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sds.ServerDeviceServer;

public interface ServerDeviceServerEncoder<T extends ServerDeviceServer> extends Encoder<T> {}
