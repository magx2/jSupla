package pl.grzeslowski.jsupla.protocol.api.decoders.dcs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderTest;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaTimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaPingServerDecoderImplTest extends DecoderTest<SuplaPingServerDecoderImpl> {
    @InjectMocks
    SuplaPingServerDecoderImpl decoder;
    @Mock
    SuplaTimevalDecoder suplaTimevalDecoder;

    @Override
    public SuplaPingServerDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(suplaTimevalDecoder.decode(any(byte[].class), anyInt())).willReturn(new SuplaTimeval(101, 102));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, final int offset) {
        verify(suplaTimevalDecoder).decode(bytes, offset);
        verifyNoMoreInteractions(suplaTimevalDecoder);
    }

    @Override
    public int entitySize() {
        return SuplaPingServer.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaPingServerDecoderImpl(null);
    }
}