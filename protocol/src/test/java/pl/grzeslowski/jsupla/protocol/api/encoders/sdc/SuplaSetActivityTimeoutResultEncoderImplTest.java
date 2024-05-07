package pl.grzeslowski.jsupla.protocol.api.encoders.sdc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaSetActivityTimeoutResultEncoderImplTest extends EncoderTest<SuplaSetActivityTimeoutResult> {
    @InjectMocks
    SuplaSetActivityTimeoutResultEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaSetActivityTimeoutResult proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.activityTimeout, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.min, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.max, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaSetActivityTimeoutResultEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaSetActivityTimeoutResult> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaSetActivityTimeoutResult getProto() {
        return new SuplaSetActivityTimeoutResult((short) 1, (short) 2, (short) 3);
    }
}