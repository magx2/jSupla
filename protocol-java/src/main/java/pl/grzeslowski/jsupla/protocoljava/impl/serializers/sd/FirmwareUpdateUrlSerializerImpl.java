package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public class FirmwareUpdateUrlSerializerImpl implements FirmwareUpdateUrlSerializer {
    private final StringSerializer stringSerializer;

    public FirmwareUpdateUrlSerializerImpl(final StringSerializer stringSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaFirmwareUpdateUrl serialize(@NotNull final FirmwareUpdateUrl entity) {
        return new SuplaFirmwareUpdateUrl(
            (byte) entity.getAvailableProtocols(),
            stringSerializer.serialize(entity.getHost(), SUPLA_URL_HOST_MAXSIZE),
            entity.getPort(),
            stringSerializer.serialize(entity.getPath(), SUPLA_URL_PATH_MAXSIZE)

        );
    }
}
