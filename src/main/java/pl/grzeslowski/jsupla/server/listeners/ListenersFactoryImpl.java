package pl.grzeslowski.jsupla.server.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequest;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestB;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.entities.responses.register_device.RegisterDeviceResponse;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class ListenersFactoryImpl implements ListenersFactory {
    private final RequestListener<DeviceRegisterRequest, RegisterDeviceResponse> deviceRegisterListener;

    public ListenersFactoryImpl(RequestListener<DeviceRegisterRequest, RegisterDeviceResponse> deviceRegisterListener) {
        this.deviceRegisterListener = requireNonNull(deviceRegisterListener);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Rq extends Request, Rsp extends Response> RequestListener<Rq, Rsp> getRequestListener(Rq request) {
        if (request instanceof DeviceRegisterRequestB) {
            return (RequestListener<Rq, Rsp>) deviceRegisterListener;
        }

        return new EmptyListener<>();
    }

    private static class EmptyListener<Rq extends Request, Rsp extends Response> implements RequestListener<Rq, Rsp> {
        private final Logger logger = LoggerFactory.getLogger(EmptyListener.class);

        @Override
        public Optional<Rsp> onRequest(Rq request) {
            logger.debug("EmptyListener for {}", request);
            return Optional.empty();
        }
    }
}
