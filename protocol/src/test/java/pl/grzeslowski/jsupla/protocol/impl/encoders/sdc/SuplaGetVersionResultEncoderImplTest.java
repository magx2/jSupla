package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaGetVersionResultEncoderImplTest extends EncoderTest<SuplaGetVersionResult> {
    @InjectMocks
    SuplaGetVersionResultEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaGetVersionResult proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.protoVersionMin, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.protoVersion, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeBytes(proto.softVer, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaGetVersionResultEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaGetVersionResult> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaGetVersionResult getProto() {
        return new SuplaGetVersionResult(
            (short) 1,
            (short) 2,
            new byte[SUPLA_SOFTVER_MAXSIZE]
        );
    }
}