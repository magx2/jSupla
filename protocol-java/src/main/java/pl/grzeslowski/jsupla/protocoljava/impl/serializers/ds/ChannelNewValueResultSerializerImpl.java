package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.ChannelNewValueResultSerializer;

import javax.validation.constraints.NotNull;

public class ChannelNewValueResultSerializerImpl implements ChannelNewValueResultSerializer {
    @Override
    public SuplaChannelNewValueResult serialize(@NotNull final ChannelNewValueResult entity) {
        return new SuplaChannelNewValueResult(
            (short) entity.getChannelNumber(),
            entity.getSenderId(),
            (byte) (entity.isSuccess() ? 1 : 0)
        );
    }
}
