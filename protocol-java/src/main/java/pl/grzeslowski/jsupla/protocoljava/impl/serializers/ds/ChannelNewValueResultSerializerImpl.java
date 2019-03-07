package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.ChannelNewValueResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

public class ChannelNewValueResultSerializerImpl implements ChannelNewValueResultSerializer {
    public static final ChannelNewValueResultSerializerImpl INSTANCE = new ChannelNewValueResultSerializerImpl();

    @SuppressWarnings("WeakerAccess")
    ChannelNewValueResultSerializerImpl() {
    }

    @Override
    public SuplaChannelNewValueResult serialize(@NotNull final ChannelNewValueResult entity) {
        return new SuplaChannelNewValueResult(
                                                     (short) entity.getChannelNumber(),
                                                     entity.getSenderId(),
                                                     (byte) (entity.isSuccess() ? 1 : 0)
        );
    }
}
