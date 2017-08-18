package pl.grzeslowski.jsupla.protocol.impl.decoders.dcs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaSetActivityTimeoutDecoderImplTest extends DecoderTest<SuplaSetActivityTimeoutDecoderImpl> {
    @InjectMocks SuplaSetActivityTimeoutDecoderImpl decoder;

    @Override
    public SuplaSetActivityTimeoutDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, final int offset) {
        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
    }

    @Override
    public int entitySize() {
        return SuplaSetActivityTimeout.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaSetActivityTimeoutDecoderImpl(null);
    }
}