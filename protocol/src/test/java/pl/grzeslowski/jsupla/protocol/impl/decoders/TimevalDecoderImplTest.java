package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class TimevalDecoderImplTest extends DecoderTest<TimevalDecoderImpl> {
    @InjectMocks TimevalDecoderImpl decoder;

    @Override
    public TimevalDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;
        verify(primitiveDecoder).parseInt(bytes, offset);
    }

    @Override
    public int entitySize() {
        return Timeval.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new TimevalDecoderImpl(null);
    }
}