package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.FirmwareUpdateParamsSerializer;

import javax.validation.constraints.NotNull;

public class FirmwareUpdateParamsSerializerImpl implements FirmwareUpdateParamsSerializer {
    @Override
    public SuplaFirmwareUpdateParams serialize(@NotNull final FirmwareUpdateParams entity) {
        return new SuplaFirmwareUpdateParams(
            (byte) entity.getPlatform(),
            entity.getParam1(),
            entity.getParam2(),
            entity.getParam3(),
            entity.getParam4()
        );
    }
}
