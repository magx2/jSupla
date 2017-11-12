package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ForInterfaceParserTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
public class ClientServerParserParserImplTest extends ForInterfaceParserTest<ClientServerEntity, ClientServer> {
    @InjectMocks ClientServerParserImpl parser;

    @Mock ChannelNewValueBParser channelNewValueBParser;
    @Mock ChannelNewValueParser channelNewValueParser;
    @Mock RegisterClientBParser registerClientBParser;
    @Mock RegisterClientParser registerClientParser;

    public ClientServerParserParserImplTest(final Class<ClientServer> protoToTestClass,
                                            final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaChannelNewValue.class, getDeclaredField(ClientServerParserParserImplTest.class,
                        "channelNewValueParser")},
                {SuplaChannelNewValueB.class, getDeclaredField(ClientServerParserParserImplTest.class,
                        "channelNewValueBParser")},
                {SuplaRegisterClient.class, getDeclaredField(ClientServerParserParserImplTest.class,
                        "registerClientParser")},
                {SuplaRegisterClientB.class, getDeclaredField(ClientServerParserParserImplTest.class,
                        "registerClientBParser")}
        });
    }

    @Override
    protected Parser<ClientServerEntity, ClientServer> getParser() {
        return parser;
    }

    @Override
    protected Class<ClientServer> suplaClass() {
        return ClientServer.class;
    }
}