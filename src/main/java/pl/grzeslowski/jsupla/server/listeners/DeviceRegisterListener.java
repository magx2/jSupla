package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequest;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestB;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestC;
import pl.grzeslowski.jsupla.server.entities.responses.RegisterDeviceResponse;

public interface DeviceRegisterListener {
    RegisterDeviceResponse onDeviceRegisterEvent(DeviceRegisterRequest event);

    RegisterDeviceResponse onDeviceRegisterEvent(DeviceRegisterRequestB event);

    RegisterDeviceResponse onDeviceRegisterEvent(DeviceRegisterRequestC event);
}
