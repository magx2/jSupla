package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaLocationPackDecoderImplTest extends DecoderTest<SuplaLocationPackDecoderImpl> {
    private static final int LOCATIONS_COUNT = 5;

    @InjectMocks SuplaLocationPackDecoderImpl decoder;
    @Mock SuplaLocationDecoder locationDecoder;

    @Override
    public SuplaLocationPackDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseInt(eq(bytes), anyInt())).willReturn(LOCATIONS_COUNT);
        given(locationDecoder.decode(eq(bytes), anyInt())).willReturn(
                new SuplaLocation((byte) 1, 2, 3, new byte[3]));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        for (int i = 0; i < LOCATIONS_COUNT; i++) {
            verify(locationDecoder).decode(bytes, offset);
            offset += BYTE_SIZE + INT_SIZE * 2 + 3;
        }
    }

    @Override
    public int entitySize() {
        return SuplaLocationPack.MIN_SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaLocationPackDecoderImpl(null, locationDecoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenLocationDecoderIsNull() throws Exception {
        new SuplaLocationPackDecoderImpl(primitiveDecoder, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesIsBiggerThanMinSizeButIsSmallerThanSizeWithLocations() {

        // given
        int offset = 5;
        byte[] bytes = new byte[offset + entitySize()];

        final int locationsCount = 3;
        given(primitiveDecoder.parseInt(eq(bytes), anyInt())).willReturn(locationsCount);

        given(locationDecoder.decode(eq(bytes), anyInt())).willReturn(
                new SuplaLocation((byte) 1, 2, 3, new byte[3]));

        // when
        decoder.decode(bytes, offset);
    }
}