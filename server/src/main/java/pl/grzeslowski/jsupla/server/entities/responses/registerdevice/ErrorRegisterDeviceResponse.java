package pl.grzeslowski.jsupla.server.entities.responses.registerdevice;

abstract class ErrorRegisterDeviceResponse extends RegisterDeviceResponse {
    ErrorRegisterDeviceResponse(int resultCode) {
        super(resultCode, 0, 0, 0);
    }
}
