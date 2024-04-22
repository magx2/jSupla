package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.*;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.AbstractSerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ServerClientSerializerImplTest extends AbstractSerializerFactoryTest<ServerClientEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {Channel.class},
            {ChannelPack.class},
            {ChannelValue.class},
            {Event.class},
            {Location.class},
            {LocationPack.class},
            {RegisterClientResult.class},
            {ChannelGroupRelation.class},
            {RegisterClientResultB.class},
            {ChannelGroupRelationPack.class},
            {ChannelB.class},
            {ChannelValuePack.class},
            {ChannelGroup.class},
            {ChannelPackB.class}
        });
    }

    @InjectMocks
    ServerClientSerializerImpl serializer;

    @Mock
    ChannelPackSerializer channelPackSerializer;
    @Mock
    ChannelSerializer channelSerializer;
    @Mock
    ChannelValueSerializer channelValueSerializer;
    @Mock
    EventSerializer eventSerializer;
    @Mock
    LocationPackSerializer locationPackSerializer;
    @Mock
    LocationSerializer locationSerializer;
    @Mock
    RegisterClientResultSerializer registerClientResultSerializer;
    @Mock
    ChannelGroupRelationSerializer channelGroupRelationSerializer;
    @Mock
    RegisterClientResultBSerializer registerClientResultBSerializer;
    @Mock
    ChannelGroupRelationPackSerializer channelGroupRelationPackSerializer;
    @Mock
    ChannelBSerializer channelBSerializer;
    @Mock
    ChannelValuePackSerializer channelValuePackSerializer;
    @Mock
    ChannelGroupSerializer channelGroupSerializer;
    @Mock
    ChannelPackBSerializer channelPackBSerializer;

    public ServerClientSerializerImplTest(final Class<ServerClientEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected Serializer<ServerClientEntity, ?> getSerializer() {
        return serializer;
    }

    @Override
    protected Class<ServerClientEntity> superInterface() {
        return ServerClientEntity.class;
    }
}