package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterEvent;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterEventB;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterEventC;
import pl.grzeslowski.jsupla.server.entities.responses.RegisterDeviceResult;

public interface DeviceRegisterListener {
    RegisterDeviceResult onDeviceRegisterEvent(DeviceRegisterEvent event);

    RegisterDeviceResult onDeviceRegisterEvent(DeviceRegisterEventB event);

    RegisterDeviceResult onDeviceRegisterEvent(DeviceRegisterEventC event);
}
