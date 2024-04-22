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
public class SuplaChannelNewValueBDecoderImplTest extends DecoderTest<SuplaChannelNewValueBDecoderImpl> {
    @InjectMocks
    SuplaChannelNewValueBDecoderImpl decoder;

    @Override
    public SuplaChannelNewValueBDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, final int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE, offset + INT_SIZE + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int entitySize() {
        return SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueBDecoderImpl(null);
    }
}