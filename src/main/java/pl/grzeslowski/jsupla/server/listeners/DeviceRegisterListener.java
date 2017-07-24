package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.DeviceRegisterEvent;
import pl.grzeslowski.jsupla.server.entities.DeviceRegisterEventB;
import pl.grzeslowski.jsupla.server.entities.DeviceRegisterEventC;
import pl.grzeslowski.jsupla.server.entities.RegisterDeviceResult;

public interface DeviceRegisterListener {
    RegisterDeviceResult onDeviceRegisterEvent(DeviceRegisterEvent event);

    RegisterDeviceResult onDeviceRegisterEvent(DeviceRegisterEventB event);

    RegisterDeviceResult onDeviceRegisterEvent(DeviceRegisterEventC event);
}
