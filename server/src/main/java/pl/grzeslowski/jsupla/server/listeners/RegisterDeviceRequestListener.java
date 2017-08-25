package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequest;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequestB;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequestC;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.RegisterDeviceResponse;

import java.util.Optional;

public interface RegisterDeviceRequestListener {
    Optional<RegisterDeviceResponse> onRegisteDeviceRequest(RegisterDeviceRequest request);

    Optional<RegisterDeviceResponse> onRegisteDeviceRequestB(RegisterDeviceRequestB request);

    Optional<RegisterDeviceResponse> onRegisteDeviceRequestC(RegisterDeviceRequestC request);
}
