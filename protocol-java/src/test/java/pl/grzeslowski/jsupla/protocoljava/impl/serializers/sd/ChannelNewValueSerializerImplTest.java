package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueSerializerImplTest extends SerializerTest<ChannelNewValue, SuplaChannelNewValue> {
    @InjectMocks ChannelNewValueSerializerImpl serializer;
    @Mock ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected ChannelNewValue given() {
        givenChannelTypeDecoder(channelTypeEncoder);
        return super.given();
    }

    @Override
    protected void then(final ChannelNewValue entity, final SuplaChannelNewValue proto) {
        assertThat(proto.senderId).isEqualTo(entity.getSenderId());
        assertThat((int) proto.channelNumber).isEqualTo(entity.getChannelNumber());
        assertThat(proto.durationMs).isEqualTo(entity.getDurationMs());
        verify(channelTypeEncoder).encode(entity.getValue());
    }

    @Override
    protected Serializer<ChannelNewValue, SuplaChannelNewValue> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelNewValue> entityClass() {
        return ChannelNewValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new ChannelNewValueSerializerImpl(null);
    }
}