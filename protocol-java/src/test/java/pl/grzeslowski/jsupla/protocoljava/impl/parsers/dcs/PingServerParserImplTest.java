package pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class PingServerParserImplTest extends ParserTest<PingServer, SuplaPingServer> {
    @InjectMocks PingServerParserImpl parser;
    @Mock TimevalParser timevalParser;

    @Override
    protected SuplaPingServer given() {
        final SuplaPingServer supla = super.given();
        BDDMockito.given(timevalParser.parse(supla.timeval)).willReturn(RANDOM_ENTITY.nextObject(Timeval.class));
        return supla;
    }

    @Override
    protected void then(final PingServer entity, final SuplaPingServer supla) {
        verify(timevalParser).parse(supla.timeval);
    }

    @Override
    protected Parser<PingServer, SuplaPingServer> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaPingServer> classToTest() {
        return SuplaPingServer.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenTimevalParserIsNull() {
        new PingServerParserImpl(null);
    }
}