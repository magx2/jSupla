package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientResultDecoderImplTest extends DecoderTest<SuplaRegisterClientResultDecoderImpl> {
    @InjectMocks SuplaRegisterClientResultDecoderImpl decoder;

    @Override
    public SuplaRegisterClientResultDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);

    }

    @Override
    public int entitySize() {
        return SuplaRegisterClientResult.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientResultDecoderImpl(null);
    }
}