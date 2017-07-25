package pl.grzeslowski.jsupla.server.listeners;

import java.awt.event.ActionListener;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public final class Listeners {
    private ActionListener actionListener;
    private DeviceRegisterListener deviceRegisterListener;
    private PingListener pingListener;
    private SuplaDataPacketListener suplaDataPacketListener;

    public Optional<ActionListener> getActionListener() {
        return ofNullable(actionListener);
    }

    public Listeners setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        return this;
    }

    public Optional<DeviceRegisterListener> getDeviceRegisterListener() {
        return ofNullable(deviceRegisterListener);
    }

    public Listeners setDeviceRegisterListener(DeviceRegisterListener deviceRegisterListener) {
        this.deviceRegisterListener = deviceRegisterListener;
        return this;
    }

    public Optional<PingListener> getPingListener() {
        return ofNullable(pingListener);
    }

    public Listeners setPingListener(PingListener pingListener) {
        this.pingListener = pingListener;
        return this;
    }

    public Optional<SuplaDataPacketListener> getSuplaDataPacketListener() {
        return Optional.ofNullable(suplaDataPacketListener);
    }

    public Listeners setSuplaDataPacketListener(SuplaDataPacketListener suplaDataPacketListener) {
        this.suplaDataPacketListener = suplaDataPacketListener;
        return this;
    }
}
