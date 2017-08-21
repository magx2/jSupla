package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class FirmwareUpdateParamsEncoderImplTest extends EncoderTest<FirmwareUpdateParams> {
    @InjectMocks FirmwareUpdateParamsEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final FirmwareUpdateParams proto) {
        int offset = 0;

        verify(primitiveEncoder).writeByte(proto.platform, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.param1, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.param2, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.param3, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.param4, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new FirmwareUpdateParamsEncoderImpl(null);
    }

    @Override
    public Encoder<FirmwareUpdateParams> getEncoder() {
        return encoder;
    }

    @Override
    public FirmwareUpdateParams getProto() {
        return new FirmwareUpdateParams((byte) 1, 5, 4, 32, 1);
    }
}