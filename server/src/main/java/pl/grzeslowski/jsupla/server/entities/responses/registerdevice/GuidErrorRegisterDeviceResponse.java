package pl.grzeslowski.jsupla.server.entities.responses.registerdevice;

import static pl.grzeslowski.jsupla.protocol.api.ResultCode.SUPLA_RESULTCODE_GUID_ERROR;

public class GuidErrorRegisterDeviceResponse extends ErrorRegisterDeviceResponse {
    public static final GuidErrorRegisterDeviceResponse GUID_ERROR = new GuidErrorRegisterDeviceResponse();

    private GuidErrorRegisterDeviceResponse() {
        super(SUPLA_RESULTCODE_GUID_ERROR);
    }
}
