package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class FirmwareUpdateUrlResultSerializerImpl implements FirmwareUpdateUrlResultSerializer {
    private final FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;

    public FirmwareUpdateUrlResultSerializerImpl(final FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer) {
        this.firmwareUpdateUrlSerializer = requireNonNull(firmwareUpdateUrlSerializer);
    }

    @Override
    public SuplaFirmwareUpdateUrlResult serialize(@NotNull final FirmwareUpdateUrlResult entity) {
        return new SuplaFirmwareUpdateUrlResult(
            (byte) (entity.isExists() ? 1 : 0),
            firmwareUpdateUrlSerializer.serialize(
                entity.getFirmwareUpdateUrl())
        );
    }
}
