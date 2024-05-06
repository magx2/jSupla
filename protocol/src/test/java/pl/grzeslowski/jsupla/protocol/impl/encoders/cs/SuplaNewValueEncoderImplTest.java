package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaNewValueEncoderImplTest extends EncoderTest<SuplaNewValue> {
    @InjectMocks
    SuplaNewValueEncoderImpl encoder;
    final SuplaNewValue proto = RANDOM_SUPLA.nextObject(SuplaNewValue.class);

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaNewValue proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.id, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeByte(proto.target, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaNewValueEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaNewValue> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaNewValue getProto() {
        return proto;
    }
}