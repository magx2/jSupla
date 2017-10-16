package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class PingServerResultClientParserImplTest
        extends ParserTest<PingServerResultClient, SuplaPingServerResultClient> {
    @InjectMocks PingServerResultClientParserImpl parser;
    @Mock TimevalParser timevalParser;

    @Override
    protected SuplaPingServerResultClient given() {
        final SuplaPingServerResultClient supla = super.given();
        BDDMockito.given(timevalParser.parse(supla.timeval))
                .willReturn(RANDOM_ENTITY.nextObject(Timeval.class));
        return supla;
    }

    @Override
    protected void then(final PingServerResultClient entity, final SuplaPingServerResultClient supla) {
        verify(timevalParser).parse(supla.timeval);
    }

    @Override
    protected Parser<PingServerResultClient, SuplaPingServerResultClient> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaPingServerResultClient> classToTest() {
        return SuplaPingServerResultClient.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenTimevalParserIsNull() {
        new PingServerResultClientParserImpl(null);
    }
}