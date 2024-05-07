package pl.grzeslowski.jsupla.protocol.api.encoders.dcs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaPingServerEncoderImplTest extends EncoderTest<SuplaPingServer> {
    @InjectMocks
    SuplaPingServerEncoderImpl encoder;
    @Mock
    TimevalEncoder timevalEncoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaPingServer proto) {
        verify(timevalEncoder).encode(proto.timeval);
        verifyNoMoreInteractions(timevalEncoder);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaPingServerEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaPingServer> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaPingServer getProto() {
        return new SuplaPingServer(new SuplaTimeval(101, 102));
    }
}