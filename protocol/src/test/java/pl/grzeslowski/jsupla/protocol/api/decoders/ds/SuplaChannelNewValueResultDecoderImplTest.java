package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueResultDecoderImplTest extends DecoderTest<SuplaChannelNewValueResultDecoderImpl> {
    @InjectMocks
    SuplaChannelNewValueResultDecoderImpl decoder;


    @Override
    public SuplaChannelNewValueResultDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseByte(bytes, offset);
    }

    @Override
    public int entitySize() {
        return SuplaChannelNewValueResult.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueResultDecoderImpl(null);
    }
}