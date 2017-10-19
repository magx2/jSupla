package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OpenClose;

public interface OpenCloseTypeEncoder {
    byte[] encode(OpenClose openClose);
}
