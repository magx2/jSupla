package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaLocationDecoderImplTest extends DecoderTest<SuplaLocationDecoderImpl> {
    private static final long CAPTION_SIZE = 3;
    @InjectMocks SuplaLocationDecoderImpl decoder;

    @Override
    public SuplaLocationDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseUnsignedInt(eq(bytes), anyInt())).willReturn(CAPTION_SIZE);
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + (int) CAPTION_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaLocation.MIN_SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaLocationDecoderImpl(null);
    }

    // @formatter:off
    @Test(expected = IllegalArgumentException.class)
    public void
        shouldShouldThrowIllegalArgumentExceptionIfBytesLengthIsBiggerThanMinSizeButSmallerThanMinSizeWIthCaption() {
        // @formatter:on

        // given
        final int offset = 5;
        final byte[] bytes = new byte[offset + entitySize() + (int) CAPTION_SIZE - 1];

        given(primitiveDecoder.parseUnsignedInt(eq(bytes), anyInt())).willReturn(CAPTION_SIZE);

        // when
        decoder.decode(bytes, offset);
    }
}