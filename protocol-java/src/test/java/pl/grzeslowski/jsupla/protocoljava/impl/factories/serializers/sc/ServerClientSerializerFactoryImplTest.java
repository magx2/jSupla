package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.sc;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.EventSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.SerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ServerClientSerializerFactoryImplTest extends SerializerFactoryTest<ServerClientEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Channel.class},
                {ChannelPack.class},
                {ChannelValue.class},
                {Event.class},
                {Location.class},
                {LocationPack.class},
                {RegisterClientResult.class}
        });
    }

    @InjectMocks ServerClientSerializerFactoryImpl factory;

    @Mock ChannelPackSerializer channelPackSerializer;
    @Mock ChannelSerializer channelSerializer;
    @Mock ChannelValueSerializer channelValueSerializer;
    @Mock EventSerializer eventSerializer;
    @Mock LocationPackSerializer locationPackSerializer;
    @Mock LocationSerializer locationSerializer;
    @Mock RegisterClientResultSerializer registerClientResultSerializer;

    public ServerClientSerializerFactoryImplTest(final Class<ServerClientEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected SerializerFactory<? super ServerClientEntity, ? extends Proto> getFactory() {
        return factory;
    }

    @Override
    protected Class<ServerClientEntity> superInterface() {
        return ServerClientEntity.class;
    }
}