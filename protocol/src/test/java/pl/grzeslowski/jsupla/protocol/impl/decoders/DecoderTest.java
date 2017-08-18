package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

public abstract class DecoderTest {
    @Mock protected PrimitiveDecoder primitiveDecoder;

    @Before
    public void init() {
        given(primitiveDecoder.copyOfRange(any(), anyInt(), anyInt())).will(invocation -> {
            final byte[] original = invocation.getArgumentAt(0, byte[].class);
            final Integer from = invocation.getArgumentAt(1, Integer.class);
            final Integer to = invocation.getArgumentAt(2, Integer.class);
            return PrimitiveDecoderImpl.INSTANCE.copyOfRange(original, from, to);
        });
    }

    @Test
    public abstract void shouldParseEntity() throws Exception ;

    @Test(expected = NullPointerException.class)
    public abstract void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception;
}
