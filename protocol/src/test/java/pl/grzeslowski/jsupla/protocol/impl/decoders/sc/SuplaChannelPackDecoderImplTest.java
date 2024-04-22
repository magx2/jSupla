package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelPackDecoderImplTest extends DecoderTest<SuplaChannelPackDecoderImpl> {
    private static final int CHANNELS_COUNT = 5;
    public static final int CAPTION_SIZE = 6;

    @InjectMocks
    SuplaChannelPackDecoderImpl decoder;
    @Mock
    SuplaChannelDecoder channelDecoder;

    @Override
    public SuplaChannelPackDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseInt(eq(bytes), anyInt())).willReturn(CHANNELS_COUNT);
        given(channelDecoder.decode(eq(bytes), anyInt())).willReturn(
            new SuplaChannel((byte) 1,
                2,
                3,
                4,
                (byte) 1,
                new SuplaChannelValue(
                    new byte[SUPLA_CHANNELVALUE_SIZE],
                    new byte[SUPLA_CHANNELVALUE_SIZE]),
                CAPTION_SIZE,
                new byte[CAPTION_SIZE]));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        for (int i = 0; i < CHANNELS_COUNT; i++) {
            verify(channelDecoder).decode(bytes, offset);
            offset += SuplaChannel.MIN_SIZE + CAPTION_SIZE;
        }

    }

    @Override
    public int entitySize() {
        return SuplaChannelPack.MIN_SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelPackDecoderImpl(null, channelDecoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenChannelDecoderIsNull() throws Exception {
        new SuplaChannelPackDecoderImpl(primitiveDecoder, null);
    }
}