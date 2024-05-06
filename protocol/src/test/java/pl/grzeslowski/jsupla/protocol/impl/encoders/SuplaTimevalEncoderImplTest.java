package pl.grzeslowski.jsupla.protocol.impl.encoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaTimevalEncoderImplTest extends EncoderTest<SuplaTimeval> {
    @InjectMocks
    TimevalEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaTimeval proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.seconds, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.milliseconds, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new TimevalEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaTimeval> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaTimeval getProto() {
        return new SuplaTimeval(101, 102);
    }
}