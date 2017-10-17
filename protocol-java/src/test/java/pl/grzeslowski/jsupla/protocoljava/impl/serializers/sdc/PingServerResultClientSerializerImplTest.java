package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
public class PingServerResultClientSerializerImplTest
        extends SerializerTest<PingServerResultClient, SuplaPingServerResultClient> {
    @InjectMocks PingServerResultClientSerializerImpl serializer;
    @Mock TimevalSerializer timevalSerializer;

    @Override
    protected PingServerResultClient given() {
        final PingServerResultClient entity = super.given();
        BDDMockito.given(timevalSerializer.serialize(entity.getTimeval()))
                .willReturn(RANDOM_SUPLA.nextObject(SuplaTimeval.class));
        return entity;
    }

    @Override
    protected void then(final PingServerResultClient entity, final SuplaPingServerResultClient proto) {
        verify(timevalSerializer).serialize(entity.getTimeval());
    }

    @Override
    protected Serializer<PingServerResultClient, SuplaPingServerResultClient> serializer() {
        return serializer;
    }

    @Override
    protected Class<PingServerResultClient> entityClass() {
        return PingServerResultClient.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenTimevalSerializerIsNull() {
        new PingServerResultClientSerializerImpl(null);
    }
}