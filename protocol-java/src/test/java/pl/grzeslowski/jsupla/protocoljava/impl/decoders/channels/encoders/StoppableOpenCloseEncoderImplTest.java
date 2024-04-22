package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.StoppableOpenClose;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class StoppableOpenCloseEncoderImplTest extends ChannelTypeEncoderTest<StoppableOpenClose> {
    @InjectMocks
    StoppableOpenCloseEncoderImpl encoder;

    @Override
    protected Class<StoppableOpenClose> getChannelValueClass() {
        return StoppableOpenClose.class;
    }

    @Override
    protected int numberOfBits() {
        return 1;
    }

    @Override
    protected byte[] encode(final StoppableOpenClose channelValue) {
        return encoder.encode(channelValue);
    }

    @Override
    protected void verify(final StoppableOpenClose channelValue, final byte[] encode) {
        switch (channelValue) {
            case STOP:
                assertThat(encode[0]).isEqualTo((byte) 0);
                break;
            case OPEN:
                assertThat(encode[0]).isEqualTo((byte) 1);
                break;
            case CLOSE:
                assertThat(encode[0]).isEqualTo((byte) 2);
                break;
            default:
                throw new UnsupportedOperationException("Don't know this type " + channelValue + "!");
        }
    }
}