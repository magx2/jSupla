package pl.grzeslowski.jsupla.server.entities.responses.register_device;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_RESULTCODE_TRUE;

public final class OkRegisterDeviceResponse extends RegisterDeviceResponse {
    public OkRegisterDeviceResponse(int activityTimeout, int version, int versionMin) {
        super(SUPLA_RESULTCODE_TRUE, activityTimeout, version, versionMin);
    }
}
