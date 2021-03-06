package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaEventDecoderImplTest extends DecoderTest<SuplaEventDecoderImpl> {
    private static final int SENDER_NAME_SIZE = 10;
    @InjectMocks SuplaEventDecoderImpl decoder;

    @Override
    public SuplaEventDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseInt(any(), anyInt())).willAnswer(__ -> SENDER_NAME_SIZE);
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SENDER_NAME_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaEvent.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaEventDecoderImpl(null);
    }
}