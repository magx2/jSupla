package pl.grzeslowski.jsupla.protocol.api.encoders.sdc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaVersionErrorEncoderImplTest extends EncoderTest<SuplaVersionError> {
    @InjectMocks
    SuplaVersionErrorEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaVersionError proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.serverVersionMin, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.serverVersion, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaVersionErrorEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaVersionError> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaVersionError getProto() {
        return new SuplaVersionError((short) 1, (short) 2);
    }
}