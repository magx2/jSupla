package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaVersionErrorDecoderImplTest extends DecoderTest<SuplaVersionErrorDecoderImpl> {
    @InjectMocks
    SuplaVersionErrorDecoderImpl decoder;

    @Override
    public SuplaVersionErrorDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
    }

    @Override
    public int entitySize() {
        return SuplaVersionError.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaVersionErrorDecoderImpl(null);
    }
}