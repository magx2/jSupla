package pl.grzeslowski.jsupla.protocol.api.encoders.sd;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaPingServerResultClientEncoderImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaPingServerResultClientEncoderImplTest extends EncoderTest<SuplaPingServerResultClient> {
    @InjectMocks
    SuplaPingServerResultClientEncoderImpl encoder;
    @Mock
    TimevalEncoder timevalEncoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaPingServerResultClient proto) {
        verify(timevalEncoder).encode(proto.timeval);
        verifyNoMoreInteractions(timevalEncoder);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceResultEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaPingServerResultClient> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaPingServerResultClient getProto() {
        return new SuplaPingServerResultClient(new SuplaTimeval(101, 102));
    }
}