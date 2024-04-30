package pl.grzeslowski.jsupla.protocol.api.traits;

public interface DeviceChannelTrait {
    /**
     * unsigned.
     */
    short getNumber();

    int getType();

    byte[] getValue();
}
