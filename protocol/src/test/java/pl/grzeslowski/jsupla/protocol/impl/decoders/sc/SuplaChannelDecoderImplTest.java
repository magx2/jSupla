package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelDecoderImplTest extends DecoderTest<SuplaChannelDecoderImpl> {
    public static final long CAPTION_SIZE = 7L;
    @InjectMocks SuplaChannelDecoderImpl decoder;
    @Mock SuplaChannelValueDecoder channelValueDecoder;

    @Override
    public SuplaChannelDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseUnsignedInt(eq(bytes), anyInt())).willReturn(CAPTION_SIZE);
        given(channelValueDecoder.decode(eq(bytes), anyInt())).willReturn(
                new SuplaChannelValue(new byte[SUPLA_CHANNELVALUE_SIZE], new byte[SUPLA_CHANNELVALUE_SIZE]));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(channelValueDecoder).decode(bytes, offset);
        offset += SuplaChannelValue.SIZE;

        verify(primitiveDecoder).parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + (int) CAPTION_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaChannel.MIN_SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelDecoderImpl(null, channelValueDecoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenChannelValueDecoderIsNull() throws Exception {
        new SuplaChannelDecoderImpl(primitiveDecoder, null);
    }
}