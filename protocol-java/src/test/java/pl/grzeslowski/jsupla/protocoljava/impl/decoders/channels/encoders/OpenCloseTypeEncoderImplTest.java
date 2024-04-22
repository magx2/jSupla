package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OpenClose;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class OpenCloseTypeEncoderImplTest extends ChannelTypeEncoderTest<OpenClose> {
    @InjectMocks
    OpenCloseTypeEncoderImpl encoder;

    @Override
    protected Class<OpenClose> getChannelValueClass() {
        return OpenClose.class;
    }

    @Override
    protected int numberOfBits() {
        return 1;
    }

    @Override
    protected byte[] encode(final OpenClose channelValue) {
        return encoder.encode(channelValue);
    }

    @Override
    protected void verify(final OpenClose channelValue, final byte[] encode) {
        assertThat(encode[0]).isEqualTo((byte) (channelValue == OpenClose.OPEN ? 1 : 0));
    }
}