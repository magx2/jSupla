package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupRelationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupRelationSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValuePackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.EventSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultSerializer;
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

    @InjectMocks ServerClientSerializerImpl serializer;

    @Mock ChannelPackSerializer channelPackSerializer;
    @Mock ChannelSerializer channelSerializer;
    @Mock ChannelValueSerializer channelValueSerializer;
    @Mock EventSerializer eventSerializer;
    @Mock LocationPackSerializer locationPackSerializer;
    @Mock LocationSerializer locationSerializer;
    @Mock RegisterClientResultSerializer registerClientResultSerializer;
    @Mock ChannelGroupRelationSerializer channelGroupRelationSerializer;
    @Mock RegisterClientResultBSerializer registerClientResultBSerializer;
    @Mock ChannelGroupRelationPackSerializer channelGroupRelationPackSerializer;
    @Mock ChannelBSerializer channelBSerializer;
    @Mock ChannelValuePackSerializer channelValuePackSerializer;
    @Mock ChannelGroupSerializer channelGroupSerializer;
    @Mock ChannelPackBSerializer channelPackBSerializer;

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