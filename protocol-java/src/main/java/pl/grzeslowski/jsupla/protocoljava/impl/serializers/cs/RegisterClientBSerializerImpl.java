package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.RegisterClientBSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class RegisterClientBSerializerImpl implements RegisterClientBSerializer {
    public static final RegisterClientBSerializerImpl INSTANCE = new RegisterClientBSerializerImpl(
            StringSerializerImpl.INSTANCE);
    private final StringSerializer stringSerializer;

    RegisterClientBSerializerImpl(final StringSerializer stringSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaRegisterClientB serialize(@NotNull final RegisterClientB entity) {
        return new SuplaRegisterClientB(
                                               entity.getAccessId(),
                                               serializePassword(entity.getAccessIdPassword(),
                                                       SUPLA_ACCESSID_PWD_MAXSIZE),
                                               serializeString(entity.getGuid(), SUPLA_GUID_SIZE),
                                               serializeString(entity.getName(), SUPLA_CLIENT_NAME_MAXSIZE),
                                               serializeString(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE),
                                               serializeString(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE)
        );
    }

    private byte[] serializeString(String string, int length) {
        return stringSerializer.serialize(string, length);
    }

    private byte[] serializePassword(char[] password, int length) {
        return stringSerializer.serializePassword(password, length);
    }
}
