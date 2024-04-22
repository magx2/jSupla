package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class ChannelValueSerializerImplTest extends SerializerTest<ChannelValue, SuplaChannelValue> {
    @InjectMocks
    ChannelValueSerializerImpl serializer;
    @Mock
    ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected ChannelValue given() {
        givenChannelTypeDecoder(channelTypeEncoder);
        return super.given();
    }

    @Override
    protected void then(final ChannelValue entity, final SuplaChannelValue proto) {
        verify(channelTypeEncoder).encode(entity.getValue());
        entity.getSubValue().ifPresent(subValue -> verify(channelTypeEncoder).encode(subValue));
    }

    @Override
    protected Serializer<ChannelValue, SuplaChannelValue> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelValue> entityClass() {
        return ChannelValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new ChannelValueSerializerImpl(null);
    }
}