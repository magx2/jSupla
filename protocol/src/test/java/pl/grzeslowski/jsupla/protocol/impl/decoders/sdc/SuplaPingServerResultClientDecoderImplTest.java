package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.decoders.dcs.SuplaPingServerDecoderImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaPingServerResultClientDecoderImplTest extends DecoderTest<SuplaPingServerResultClientDecoderImpl> {
    @InjectMocks SuplaPingServerResultClientDecoderImpl decoder;
    @Mock TimevalDecoder timevalDecoder;

    @Override
    public SuplaPingServerResultClientDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(timevalDecoder.decode(any(byte[].class), anyInt())).willReturn(new Timeval(101, 102));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, final int offset) {
        verify(timevalDecoder).decode(bytes, offset);
        verifyNoMoreInteractions(timevalDecoder);
    }

    @Override
    public int entitySize() {
        return SuplaPingServerResultClient.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaPingServerDecoderImpl(null);
    }

}