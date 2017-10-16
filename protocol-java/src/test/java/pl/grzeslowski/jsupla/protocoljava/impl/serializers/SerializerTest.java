package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public abstract class SerializerTest<EntityT extends Entity, SuplaProtoT extends Proto> {

    @Test
    public void shouldSerializeEntity() throws Exception {

        // given
        final EntityT entity = given();

        // when
        final @NotNull SuplaProtoT proto = serializer().serialize(entity);

        // then
        assertThat(proto).isNotNull();
        then(entity, proto);
    }

    protected EntityT given() {
        return RANDOM_ENTITY.nextObject(entityClass());
    }

    protected void givenStringSerializer(StringSerializer stringSerializer) {
        BDDMockito.given(stringSerializer.serialize(any(), anyInt()))
                .will(invocationOnMock -> new byte[invocationOnMock.getArgumentAt(1, Integer.class)]);
        BDDMockito.given(stringSerializer.serialize(any()))
                .will(invocationOnMock -> new byte[invocationOnMock.getArgumentAt(0, String.class).length()]);
        BDDMockito.given(stringSerializer.serializePassword(any(), anyInt()))
                .will(invocationOnMock -> new byte[invocationOnMock.getArgumentAt(1, Integer.class)]);
    }

    protected void givenChannelTypeDecoder(ChannelTypeEncoder channelTypeEncoder) {
        BDDMockito.given(channelTypeEncoder.encode(any())).willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
    }

    abstract protected void then(EntityT entity, SuplaProtoT proto);

    abstract protected Serializer<EntityT, SuplaProtoT> serializer();

    abstract protected Class<EntityT> entityClass();
}
