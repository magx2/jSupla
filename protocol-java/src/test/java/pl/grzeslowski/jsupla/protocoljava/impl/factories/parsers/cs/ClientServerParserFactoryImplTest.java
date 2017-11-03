package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.cs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.FactoryTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
public class ClientServerParserFactoryImplTest extends FactoryTest<ClientServerEntity, ClientServer> {
    @InjectMocks ClientServerParserFactoryImpl factory;

    @Mock ChannelNewValueBParser channelNewValueBParser;
    @Mock ChannelNewValueParser channelNewValueParser;
    @Mock RegisterClientBParser registerClientBParser;
    @Mock RegisterClientParser registerClientParser;

    public ClientServerParserFactoryImplTest(final Class<ClientServer> protoToTestClass, final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaChannelNewValue.class, getDeclaredField(ClientServerParserFactoryImplTest.class,
                        "channelNewValueParser")},
                {SuplaChannelNewValueB.class, getDeclaredField(ClientServerParserFactoryImplTest.class,
                        "channelNewValueBParser")},
                {SuplaRegisterClient.class, getDeclaredField(ClientServerParserFactoryImplTest.class,
                        "registerClientParser")},
                {SuplaRegisterClientB.class, getDeclaredField(ClientServerParserFactoryImplTest.class,
                        "registerClientBParser")}
        });
    }

    @Override
    protected ParserFactory<ClientServerEntity, ClientServer> parserFactory() {
        return factory;
    }

    @Override
    protected Class<ClientServer> suplaClass() {
        return ClientServer.class;
    }
}