package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.mockito.Mockito.verify;


public class RegisterClientCParserImplTest extends AbstractParserTest<RegisterClientC, SuplaRegisterClientC> {
    @InjectMocks
    RegisterClientCParserImpl parser;

    @Mock
    StringParser stringParser;

    @Before
    public void setUp() {
        givenStringParser(stringParser);
    }

    @Override
    protected void then(RegisterClientC entity, SuplaRegisterClientC supla) {
        verify(stringParser).parse(supla.email);
        verify(stringParser).parse(supla.authKey);
        verify(stringParser).parse(supla.guid);
        verify(stringParser).parse(supla.name);
        verify(stringParser).parse(supla.softVer);
        verify(stringParser).parse(supla.serverName);
    }

    @Override
    protected Parser<RegisterClientC, SuplaRegisterClientC> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterClientC> classToTest() {
        return SuplaRegisterClientC.class;
    }
}