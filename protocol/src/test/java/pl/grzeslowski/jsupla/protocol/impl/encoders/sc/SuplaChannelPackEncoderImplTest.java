package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelPackEncoderImplTest extends EncoderTest<SuplaChannelPack> {
    @InjectMocks
    SuplaChannelPackEncoderImpl encoder;
    @Mock
    SuplaChannelEncoder channelEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        // @formatter:off
        given(channelEncoder.encode(any(SuplaChannel.class))).willAnswer(
            invocation -> new byte[invocation.getArgumentAt(0, SuplaChannel.class).size()]
        );
        // @formatter:on
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelPack proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.count, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.totalLeft, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        for (SuplaChannel channel : proto.channels) {
            verify(channelEncoder).encode(channel);
            verify(primitiveEncoder).writeBytes(new byte[channel.size()], bytesToWriteInto(), offset);
            offset += channel.size();
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelPackEncoderImpl(null, channelEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaChannelEncoderIsNull() throws Exception {
        new SuplaChannelPackEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannelPack> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelPack getProto() {
        final int count = 5;
        final SuplaChannel[] channels = new SuplaChannel[count];
        for (int i = 0; i < count; i++) {
            final SuplaChannelValue value = new SuplaChannelValue(
                new byte[SUPLA_CHANNELVALUE_SIZE],
                new byte[SUPLA_CHANNELVALUE_SIZE]);
            final int captionSize = i * 10;
            final byte[] caption = new byte[captionSize];
            channels[i] = new SuplaChannel(
                (byte) 1,
                i * 2 + 1,
                i * 3,
                i * 4,
                (byte) 2,
                value,
                captionSize,
                caption);
        }
        return new SuplaChannelPack(count, 2, channels);
    }
}