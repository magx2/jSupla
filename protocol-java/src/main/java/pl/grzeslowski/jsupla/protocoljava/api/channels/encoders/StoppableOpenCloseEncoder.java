package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.StoppableOpenClose;

public interface StoppableOpenCloseEncoder {
    byte[] encode(StoppableOpenClose stoppableOpenClose);
}
