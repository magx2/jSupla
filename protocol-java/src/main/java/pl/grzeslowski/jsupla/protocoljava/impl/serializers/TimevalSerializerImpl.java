package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

public class TimevalSerializerImpl implements TimevalSerializer {
    public static final TimevalSerializerImpl INSTANCE = new TimevalSerializerImpl();
    
    @Override
    public SuplaTimeval serialize(@NotNull final Timeval entity) {
        return new SuplaTimeval(entity.getSeconds(), entity.getMilliseconds());
    }
}
