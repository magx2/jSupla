package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.GetVersionResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class GetVersionResultSerializerImpl implements GetVersionResultSerializer {
    public static final GetVersionResultSerializerImpl INSTANCE = new GetVersionResultSerializerImpl(
            StringSerializerImpl.INSTANCE);
    private final StringSerializer stringSerializer;

    GetVersionResultSerializerImpl(final StringSerializer stringSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaGetVersionResult serialize(@NotNull final GetVersionResult entity) {
        return new SuplaGetVersionResult(
                                                (short) entity.getProtoVersionMin(),
                                                (short) entity.getProtoVersion(),
                                                stringSerializer.serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE)
        );
    }
}
