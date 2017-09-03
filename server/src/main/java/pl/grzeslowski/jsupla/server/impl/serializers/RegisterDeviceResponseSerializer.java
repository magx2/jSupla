package pl.grzeslowski.jsupla.server.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.server.api.entities.responses.registerdevice.RegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.api.serializers.Serializer;

public class RegisterDeviceResponseSerializer implements Serializer<RegisterDeviceResponse, SuplaRegisterDeviceResult> {
    @Override
    public SuplaRegisterDeviceResult serialize(RegisterDeviceResponse entity) {
        int resultCode = entity.getResultCode().getValue();
        byte activityTimeout = (byte) entity.getActivityTimeout();
        byte version = (byte) entity.getVersion();
        byte versionMin = (byte) entity.getVersionMin();

        return new SuplaRegisterDeviceResult(resultCode, activityTimeout, version, versionMin);
    }
}
