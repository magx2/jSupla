package pl.grzeslowski.jsupla.protocol.api.encoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientResultEncoderImplTest extends EncoderTest<SuplaRegisterClientResult> {
    @InjectMocks
    SuplaRegisterClientResultEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterClientResult proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.resultCode, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.clientId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.locationCount, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.channelCount, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.activityTimeout, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.version, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.versionMin, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientResultEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaRegisterClientResult> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterClientResult getProto() {
        return new SuplaRegisterClientResult(
            1,
            2,
            3,
            4,
            (short) 5,
            (short) 7,
            (short) 6);
    }
}