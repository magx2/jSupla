package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class PingServerSerializerImpl implements PingServerSerializer {
    private final TimevalSerializer timevalSerializer;

    public PingServerSerializerImpl(final TimevalSerializer timevalSerializer) {
        this.timevalSerializer = requireNonNull(timevalSerializer);
    }

    @Override
    public SuplaPingServer serialize(@NotNull final PingServer entity) {
        return new SuplaPingServer(timevalSerializer.serialize(entity.getTimeval()));
    }
}
