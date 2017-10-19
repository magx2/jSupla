package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.LONG_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaTimevalDecoderImplTest extends DecoderTest<TimevalDecoderImpl> {
    @InjectMocks TimevalDecoderImpl decoder;

    @Override
    public TimevalDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseLong(bytes, offset);
        offset += LONG_SIZE;
        verify(primitiveDecoder).parseLong(bytes, offset);
    }

    @Override
    public int entitySize() {
        return SuplaTimeval.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new TimevalDecoderImpl(null);
    }
}