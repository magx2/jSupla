package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.cs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.SerializerFactoryTest;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientSerializerImpl;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ClientServerSerializerFactoryImplTest extends SerializerFactoryTest<ClientServerEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ChannelNewValue.class},
                {RegisterClientB.class},
                {RegisterClient.class},
                {ChannelNewValueB.class},
        });
    }

    @InjectMocks ClientServerSerializerFactoryImpl factory;

    @Mock ChannelNewValueSerializerImpl channelNewValueSerializer;
    @Mock ChannelNewValueBSerializerImpl channelNewValueBSerializer;
    @Mock RegisterClientSerializerImpl registerClientSerializer;
    @Mock RegisterClientBSerializerImpl registerClientBSerializer;

    public ClientServerSerializerFactoryImplTest(final Class<ClientServerEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected SerializerFactory<? super ClientServerEntity, ? extends Proto> getFactory() {
        return factory;
    }

    @Override
    protected Class<ClientServerEntity> superInterface() {
        return ClientServerEntity.class;
    }
}