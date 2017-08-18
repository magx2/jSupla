package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB.SIZE;

@SuppressWarnings({"WeakerAccess", "ResultOfMethodCallIgnored"})
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueBDecoderImplTest extends DecoderTest {
    @InjectMocks SuplaChannelNewValueBDecoderImpl decoder;

    @Override
    public void shouldParseEntity() throws Exception {

        // given
        final byte[] bytes = new byte[SIZE];

        // when
        decoder.decode(bytes, 0);

        // then
        verify(primitiveDecoder).parseInt(bytes, 0);
        verify(primitiveDecoder).copyOfRange(bytes, INT_SIZE, INT_SIZE + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueBDecoderImpl(null);
    }

    @Override
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmall() throws Exception {

        // given
        final byte[] bytes = new byte[SIZE - 1];

        // when
        decoder.decode(bytes, 0);
    }

    @Override
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmallAfterAddingOffset() throws Exception {

        // given
        final byte[] bytes = new byte[SIZE];

        // when
        decoder.decode(bytes, 1);
    }
}