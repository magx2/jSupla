package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.PercentValue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class PercentTypeChannelEncoderImplTest extends ChannelTypeEncoderTest<PercentValue> {
    @InjectMocks PercentTypeChannelEncoderImpl encoder;

    @Override
    protected Class<PercentValue> getChannelValueClass() {
        return PercentValue.class;
    }

    @Override
    protected int numberOfBits() {
        return 1;
    }

    @Override
    protected byte[] encode(final PercentValue channelValue) {
        return encoder.encode(channelValue);
    }

    @Override
    protected void verify(final PercentValue channelValue, final byte[] encode) {
        assertThat(encode[0]).isEqualTo((byte) (10 + channelValue.getValue()));
    }
}