package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class RelayTypeChannelEncoderImplTest extends ChannelTypeEncoderTest<OnOff> {
    @InjectMocks
    RelayTypeChannelEncoderImpl encoder;

    @Override
    protected Class<OnOff> getChannelValueClass() {
        return OnOff.class;
    }

    @Override
    protected int numberOfBits() {
        return 1;
    }

    @Override
    protected byte[] encode(final OnOff channelValue) {
        return encoder.encode(channelValue);
    }

    @Override
    protected void verify(final OnOff channelValue, final byte[] encode) {
        switch (channelValue) {
            case ON:
                assertThat(encode[0]).isEqualTo((byte) 1);
                break;
            case OFF:
                assertThat(encode[0]).isEqualTo((byte) 0);
                break;
            default:
                throw new UnsupportedOperationException("Don't know this OnOff type " + channelValue + "!");
        }
    }
}