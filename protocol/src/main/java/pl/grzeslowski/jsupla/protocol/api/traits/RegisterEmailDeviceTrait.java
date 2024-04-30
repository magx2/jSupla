package pl.grzeslowski.jsupla.protocol.api.traits;

public interface RegisterEmailDeviceTrait extends RegisterDeviceTrait {
    byte[] getAuthKey();

    byte[] getEmail();

    byte[] getServerName();
}
