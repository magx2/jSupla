package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@RunWith(MockitoJUnitRunner.class)
public abstract class ChannelTypeEncoderTest<T extends ChannelValue> {

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueIsNull() {
        encode(null);
    }

    @Test
    public void shouldParseChannelType() throws Exception {

        // given
        T channelValue = randomChannelValue();

        // when
        final byte[] encode = encode(channelValue);

        // then
        assertThat(encode).hasSize(SUPLA_CHANNELVALUE_SIZE);

        verify(channelValue, encode);

        for (int i = numberOfBits(); i < SUPLA_CHANNELVALUE_SIZE; i++) {
            assertThat(encode[i]).isEqualTo((byte) 0);
        }
    }

    private T randomChannelValue() {
        return RANDOM_ENTITY.nextObject(getChannelValueClass());
    }

    protected abstract Class<T> getChannelValueClass();

    protected abstract int numberOfBits();

    protected abstract byte[] encode(final T channelValue);

    protected abstract void verify(final T channelValue, final byte[] encode);
}
