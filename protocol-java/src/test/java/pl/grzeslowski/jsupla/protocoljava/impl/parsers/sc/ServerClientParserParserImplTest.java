package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.EventParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.RegisterClientResultParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ForInterfaceParserTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ServerClientParserParserImplTest extends ForInterfaceParserTest<ServerClientEntity, ServerClient> {
    @InjectMocks ServerClientParserImpl parser;

    @Mock LocationParser locationParser;
    @Mock ChannelPackParser channelPackParser;
    @Mock EventParser eventParser;
    @Mock ChannelValueParser channelValueParser;
    @Mock ChannelParser channelParser;
    @Mock LocationPackParser locationPackParser;
    @Mock RegisterClientResultParser registerClientResultParser;

    public ServerClientParserParserImplTest(final Class<ServerClient> protoToTestClass,
                                            final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaChannel.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "channelParser")},
                {SuplaChannelPack.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "channelPackParser")},
                {SuplaChannelValue.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "channelValueParser")},
                {SuplaEvent.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "eventParser")},
                {SuplaLocation.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "locationParser")},
                {SuplaLocationPack.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "locationPackParser")},
                {SuplaRegisterClientResult.class,
                        getDeclaredField(ServerClientParserParserImplTest.class, "registerClientResultParser")}
        });
    }

    @Override
    protected Parser<ServerClientEntity, ServerClient> getParser() {
        return parser;
    }

    @Override
    protected Class<ServerClient> suplaClass() {
        return ServerClient.class;
    }
}