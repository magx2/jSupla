package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;

public interface EncoderFactory {
    <SD extends ServerDevice> Encoder<SD> getEncoderForServerDevice(SD sd);
}
