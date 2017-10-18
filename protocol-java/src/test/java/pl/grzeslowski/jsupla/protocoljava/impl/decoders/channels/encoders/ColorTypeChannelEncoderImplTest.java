package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@SuppressWarnings("WeakerAccess")
public class ColorTypeChannelEncoderImplTest extends ChannelTypeEncoderTest<RgbValue> {
    @InjectMocks ColorTypeChannelEncoderImpl encoder;

    @Override
    protected Class<RgbValue> getChannelValueClass() {
        return RgbValue.class;
    }

    @Override
    protected int numberOfBits() {
        return 5;
    }

    @Override
    protected byte[] encode(final RgbValue channelValue) {
        return encoder.encode(channelValue);
    }

    @Override
    protected void verify(final RgbValue channelValue, final byte[] encode) {
        final byte[] expectedBytes = prepareExpectedBytes(channelValue);

        for (int i = 0; i < expectedBytes.length; i++) {
            assertThat(encode[i]).isEqualTo(expectedBytes[i]);
        }
    }

    @SuppressWarnings("UnusedAssignment")
    private byte[] prepareExpectedBytes(final RgbValue channelValue) {
        final byte[] bytes = new byte[5];
        int offset = 0;

        offset += INSTANCE.writeUnsignedByte((short) channelValue.brightness, bytes, offset);
        offset += INSTANCE.writeUnsignedByte((short) channelValue.colorBrightness, bytes, offset);
        offset += INSTANCE.writeUnsignedByte((short) channelValue.red, bytes, offset);
        offset += INSTANCE.writeUnsignedByte((short) channelValue.green, bytes, offset);
        offset += INSTANCE.writeUnsignedByte((short) channelValue.blue, bytes, offset);

        return bytes;
    }
}