package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.AbstractSerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ClientServerSerializerImplTest extends AbstractSerializerFactoryTest<ClientServerEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ChannelNewValue.class},
                {RegisterClientB.class},
                {RegisterClient.class},
                {ChannelNewValueB.class},
        });
    }

    @InjectMocks ClientServerSerializerImpl serializer;

    @Mock ChannelNewValueSerializerImpl channelNewValueSerializer;
    @Mock ChannelNewValueBSerializerImpl channelNewValueBSerializer;
    @Mock RegisterClientSerializerImpl registerClientSerializer;
    @Mock RegisterClientBSerializerImpl registerClientBSerializer;

    public ClientServerSerializerImplTest(final Class<ClientServerEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected Serializer<ClientServerEntity, ?> getSerializer() {
        return serializer;
    }

    @Override
    protected Class<ClientServerEntity> superInterface() {
        return ClientServerEntity.class;
    }
}