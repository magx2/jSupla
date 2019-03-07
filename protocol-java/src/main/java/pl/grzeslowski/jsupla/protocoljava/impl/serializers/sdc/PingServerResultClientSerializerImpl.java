package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.PingServerResultClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.TimevalSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class PingServerResultClientSerializerImpl implements PingServerResultClientSerializer {
    public static final PingServerResultClientSerializerImpl INSTANCE = new PingServerResultClientSerializerImpl(
            TimevalSerializerImpl.INSTANCE);
    private final TimevalSerializer timevalSerializer;

    PingServerResultClientSerializerImpl(final TimevalSerializer timevalSerializer) {
        this.timevalSerializer = requireNonNull(timevalSerializer);
    }

    @Override
    public SuplaPingServerResultClient serialize(@NotNull final PingServerResultClient entity) {
        return new SuplaPingServerResultClient(
                                                      timevalSerializer.serialize(entity.getTimeval())
        );
    }
}
