package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceResultEncoderImplTest extends EncoderTest<SuplaRegisterDeviceResult> {
    @InjectMocks
    SuplaRegisterDeviceResultEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterDeviceResult proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.resultCode, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeByte(proto.activityTimeout, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeByte(proto.version, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeByte(proto.versionMin, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceResultEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaRegisterDeviceResult> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterDeviceResult getProto() {
        return new SuplaRegisterDeviceResult(
            1,
            (byte) 2,
            (byte) 4,
            (byte) 3

        );
    }
}