package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.structs.sd.TSD_SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.server.entities.responses.register_device.RegisterDeviceResponse;

class RegisterDeviceResponseSerializer implements Serializer<RegisterDeviceResponse, TSD_SuplaRegisterDeviceResult> {
    @Override
    public TSD_SuplaRegisterDeviceResult serialize(RegisterDeviceResponse entity) {
        int resultCode = entity.getResultCode();
        byte activityTimeout = (byte) entity.getActivityTimeout();
        byte version = (byte) entity.getVersion();
        byte versionMin = (byte) entity.getVersionMin();

        return new TSD_SuplaRegisterDeviceResult(resultCode, activityTimeout, version, versionMin);
    }
}
