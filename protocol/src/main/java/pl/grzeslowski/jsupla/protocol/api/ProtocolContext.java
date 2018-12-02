package pl.grzeslowski.jsupla.protocol.api;

import pl.grzeslowski.jsupla.JSuplaContext;
import pl.grzeslowski.jsupla.exceptions.ServiceNotFoundException;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ProtocolContext implements JSuplaContext {
    PROTOCOL_CONTEXT;

    private final Map<Class<?>, Object> contextMap = new ConcurrentHashMap<>();

    ProtocolContext() {
        // primitives
        contextMap.put(PrimitiveDecoder.class, PrimitiveDecoderImpl.INSTANCE);
        contextMap.put(PrimitiveEncoder.class, PrimitiveEncoderImpl.INSTANCE);

        // decoders
        contextMap.put(DecoderFactory.class, DecoderFactoryImpl.INSTANCE);
        contextMap.put(EncoderFactory.class, EncoderFactoryImpl.INSTANCE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getService(final Class<T> serviceClass) throws ServiceNotFoundException {
        Object service = contextMap.get(serviceClass);
        if (service == null) {
            throw new ServiceNotFoundException(serviceClass);
        }
        return (T) service;
    }
}
