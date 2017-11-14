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
                                                    (short) entity.getParam1(),
                                                    (short) entity.getParam2(),
                                                    (short) entity.getParam3(),
                                                    (short) entity.getParam4()
        );
    }
}
