package pl.grzeslowski.jsupla.server.entities.responses.register_device;

abstract class ErrorRegisterDeviceResponse extends RegisterDeviceResponse {
    ErrorRegisterDeviceResponse(int resultCode) {
        super(resultCode, 0, 0, 0);
    }
}
