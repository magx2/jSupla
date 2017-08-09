package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.RegisterDeviceResponse;

class RegisterDeviceResponseSerializer implements Serializer<RegisterDeviceResponse, SuplaRegisterDeviceResult> {
    @Override
    public SuplaRegisterDeviceResult serialize(RegisterDeviceResponse entity) {
        int resultCode = entity.getResultCode();
        byte activityTimeout = (byte) entity.getActivityTimeout();
        byte version = (byte) entity.getVersion();
        byte versionMin = (byte) entity.getVersionMin();

        return new SuplaRegisterDeviceResult(resultCode, activityTimeout, version, versionMin);
    }
}
