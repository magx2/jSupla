package pl.grzeslowski.jsupla.server.api.entities.responses.registerdevice;

import pl.grzeslowski.jsupla.protocol.api.ResultCode;

abstract class ErrorRegisterDeviceResponse extends RegisterDeviceResponse {
    ErrorRegisterDeviceResponse(ResultCode resultCode) {
        super(resultCode, 0, 0, 0);
    }
}
