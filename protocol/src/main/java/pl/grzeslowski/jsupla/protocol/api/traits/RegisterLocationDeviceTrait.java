package pl.grzeslowski.jsupla.protocol.api.traits;

public interface RegisterLocationDeviceTrait extends RegisterDeviceTrait {
    int getLocationId();

    byte[] getLocationPwd();
}
