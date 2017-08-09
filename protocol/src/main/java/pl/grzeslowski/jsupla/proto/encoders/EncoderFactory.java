package pl.grzeslowski.jsupla.proto.encoders;

import pl.grzeslowski.jsupla.proto.structs.sd.ServerDevice;

public interface EncoderFactory {
    <SD extends ServerDevice> Encoder<SD> getEncoderForServerDevice(SD sd);
}
