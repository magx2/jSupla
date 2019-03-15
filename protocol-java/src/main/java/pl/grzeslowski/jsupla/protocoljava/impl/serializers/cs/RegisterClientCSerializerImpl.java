package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.RegisterClientCSerializer;

import javax.validation.constraints.NotNull;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class RegisterClientCSerializerImpl implements RegisterClientCSerializer {
    private final StringSerializer stringSerializer;

    public RegisterClientCSerializerImpl(StringSerializer stringSerializer) {
        this.stringSerializer = stringSerializer;
    }

    @Override
    public SuplaRegisterClientC serialize(@NotNull RegisterClientC entity) {
        return new SuplaRegisterClientC(
                stringSerializer.serialize(entity.getEmail(), SUPLA_EMAIL_MAXSIZE),
                stringSerializer.serialize(entity.getAuthKey(), SUPLA_AUTHKEY_SIZE),
                stringSerializer.serialize(entity.getGuid(), SUPLA_GUID_SIZE),
                stringSerializer.serialize(entity.getName(), SUPLA_CLIENT_NAME_MAXSIZE),
                stringSerializer.serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE),
                stringSerializer.serialize(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE)
        );
    }
}
