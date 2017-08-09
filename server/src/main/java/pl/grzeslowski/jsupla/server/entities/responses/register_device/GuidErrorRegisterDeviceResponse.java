package pl.grzeslowski.jsupla.server.entities.responses.register_device;

import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_RESULTCODE_GUID_ERROR;

public class GuidErrorRegisterDeviceResponse extends ErrorRegisterDeviceResponse {
    public static final GuidErrorRegisterDeviceResponse GUID_ERROR = new GuidErrorRegisterDeviceResponse();

    private GuidErrorRegisterDeviceResponse() {
        super(SUPLA_RESULTCODE_GUID_ERROR);
    }
}
