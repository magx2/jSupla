package pl.grzeslowski.jsupla.server.netty.impl;

import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.JSuplaContext;
import pl.grzeslowski.jsupla.exceptions.ServiceNotFoundException;
import pl.grzeslowski.jsupla.server.netty.api.TypeMapper;

@RequiredArgsConstructor
public class ProtocolJavaContext implements JSuplaContext {
    private final TypeMapper typeMapper;

    @Override
    public <T> T getService(Class<T> serviceClass) throws ServiceNotFoundException {
        // todo implemetn
        return null;
    }
}
