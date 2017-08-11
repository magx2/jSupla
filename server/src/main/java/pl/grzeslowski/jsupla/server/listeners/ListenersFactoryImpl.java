package pl.grzeslowski.jsupla.server.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequest;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequestB;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.RegisterDeviceResponse;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class ListenersFactoryImpl implements ListenersFactory {
    private final RequestListener<RegisterDeviceRequest, RegisterDeviceResponse> deviceRegisterListener;

    public ListenersFactoryImpl(RequestListener<RegisterDeviceRequest, RegisterDeviceResponse> deviceRegisterListener) {
        this.deviceRegisterListener = requireNonNull(deviceRegisterListener);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RqT extends Request, RspT extends Response> RequestListener<RqT, RspT> getRequestListener(RqT request) {
        if (request instanceof RegisterDeviceRequestB) {
            return (RequestListener<RqT, RspT>) deviceRegisterListener;
        }

        return new EmptyListener<>();
    }

    private static class EmptyListener<RqT extends Request, RspT extends Response>
            implements RequestListener<RqT, RspT> {
        private final Logger logger = LoggerFactory.getLogger(EmptyListener.class);

        @Override
        public Optional<RspT> onRequest(RqT request) {
            logger.debug("EmptyListener for {}", request);
            return Optional.empty();
        }
    }
}
