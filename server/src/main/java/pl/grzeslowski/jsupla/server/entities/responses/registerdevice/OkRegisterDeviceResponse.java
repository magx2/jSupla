package pl.grzeslowski.jsupla.server.entities.responses.registerdevice;

import static pl.grzeslowski.jsupla.protocol.ResultCode.SUPLA_RESULTCODE_TRUE;

public final class OkRegisterDeviceResponse extends RegisterDeviceResponse {
    public OkRegisterDeviceResponse(int activityTimeout, int version, int versionMin) {
        super(SUPLA_RESULTCODE_TRUE, activityTimeout, version, versionMin);
    }
}
