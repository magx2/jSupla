package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class PingServerSerializerImplTest extends SerializerTest<PingServer, SuplaPingServer> {
    @InjectMocks
    PingServerSerializerImpl serializer;
    @Mock
    TimevalSerializer timevalSerializer;

    @Override
    protected PingServer given() {
        final PingServer entity = super.given();
        BDDMockito.given(timevalSerializer.serialize(entity.getTimeval()))
            .willReturn(RANDOM_ENTITY.nextObject(SuplaTimeval.class));
        return entity;
    }

    @Override
    protected void then(final PingServer entity, final SuplaPingServer proto) {
        verify(timevalSerializer).serialize(entity.getTimeval());
        verifyNoMoreInteractions(timevalSerializer);
    }

    @Override
    protected Serializer<PingServer, SuplaPingServer> serializer() {
        return serializer;
    }

    @Override
    protected Class<PingServer> entityClass() {
        return PingServer.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenTimevalSerializerIsNull() {
        new PingServerSerializerImpl(null);
    }
}