package pl.grzeslowski.jsupla.protocol.impl.encoders.dcs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaSetActivityTimeoutEncoderImplTest extends EncoderTest<SuplaSetActivityTimeout> {
    @InjectMocks SuplaSetActivityTimeoutEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaSetActivityTimeout proto) {
        verify(primitiveEncoder).writeUnsignedByte(proto.activityTimeout, new byte[proto.size()], 0);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaSetActivityTimeoutEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaSetActivityTimeout> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaSetActivityTimeout getProto() {
        return new SuplaSetActivityTimeout((short) 100);
    }
}