package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

public abstract class DecoderTest<T extends Decoder<?>> {
    @Mock protected PrimitiveDecoder primitiveDecoder;

    @Before
    public void init() {
        given(primitiveDecoder.copyOfRange(any(), anyInt(), anyInt())).will(invocation -> {
            final byte[] original = invocation.getArgumentAt(0, byte[].class);
            final Integer from = invocation.getArgumentAt(1, Integer.class);
            final Integer to = invocation.getArgumentAt(2, Integer.class);
            final int newLength = to - from;
            return new byte[newLength];
        });
    }

    public abstract T getDecoder();

    public abstract void verifyParseEntity(final byte[] bytes, final int offset);

    public abstract int entitySize();

    @Test
    public void shouldParseEntity() throws Exception {

        // given
        int offset = 5;
        byte[] bytes = new byte[entitySize() + offset];

        // when
        getDecoder().decode(bytes, offset);

        // then
        verifyParseEntity(bytes, offset);
    }

    @Test(expected = NullPointerException.class)
    public abstract void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmall() throws Exception {

        // given
        final byte[] bytes = new byte[SuplaChannelNewValueB.SIZE - 1];

        // when
        getDecoder().decode(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmallAfterAddingOffset() throws Exception {

        // given
        final byte[] bytes = new byte[SuplaChannelNewValueB.SIZE];

        // when
        getDecoder().decode(bytes, 1);
    }
}
