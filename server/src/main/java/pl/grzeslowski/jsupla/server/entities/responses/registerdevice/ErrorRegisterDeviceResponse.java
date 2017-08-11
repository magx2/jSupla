package pl.grzeslowski.jsupla.server.entities.responses.registerdevice;

import pl.grzeslowski.jsupla.protocol.ResultCode;

abstract class ErrorRegisterDeviceResponse extends RegisterDeviceResponse {
    ErrorRegisterDeviceResponse(ResultCode resultCode) {
        super(resultCode, 0, 0, 0);
    }
}
