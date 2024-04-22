package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.NewValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.RegisterClientCSerializer;
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
            {RegisterClientC.class},
            {NewValue.class},
        });
    }

    @InjectMocks
    ClientServerSerializerImpl serializer;

    @Mock
    ChannelNewValueSerializerImpl channelNewValueSerializer;
    @Mock
    ChannelNewValueBSerializerImpl channelNewValueBSerializer;
    @Mock
    RegisterClientSerializerImpl registerClientSerializer;
    @Mock
    RegisterClientBSerializerImpl registerClientBSerializer;
    @Mock
    RegisterClientCSerializer registerClientCSerializer;
    @Mock
    NewValueSerializer newValueSerializer;

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