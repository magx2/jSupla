package pl.grzeslowski.jsupla.protocol.impl.encoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.LONG_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class TimevalEncoderImplTest extends EncoderTest<Timeval> {
    @InjectMocks TimevalEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final Timeval proto) {
        int offset = 0;

        verify(primitiveEncoder).writeLong(proto.seconds, bytesToWriteInto(), offset);
        offset += LONG_SIZE;
        verify(primitiveEncoder).writeLong(proto.milliseconds, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new TimevalEncoderImpl(null);
    }

    @Override
    public Encoder<Timeval> getEncoder() {
        return encoder;
    }

    @Override
    public Timeval getProto() {
        return new Timeval(101, 102);
    }
}