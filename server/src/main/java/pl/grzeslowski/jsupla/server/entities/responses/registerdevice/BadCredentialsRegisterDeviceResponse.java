package pl.grzeslowski.jsupla.server.entities.responses.registerdevice;

import static pl.grzeslowski.jsupla.protocol.api.ResultCode.SUPLA_RESULTCODE_BAD_CREDENTIALS;

public final class BadCredentialsRegisterDeviceResponse extends ErrorRegisterDeviceResponse {
    public static final BadCredentialsRegisterDeviceResponse BAD_CREDENTIALS =
            new BadCredentialsRegisterDeviceResponse();

    private BadCredentialsRegisterDeviceResponse() {
        super(SUPLA_RESULTCODE_BAD_CREDENTIALS);
    }
}
