package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.GetVersionResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.PingServerResultClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.SetActivityTimeoutResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.VersionErrorParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ForInterfaceParserTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ServerDeviceClientParserParserImplTest
    extends ForInterfaceParserTest<ServerDeviceClientEntity, ServerDeviceClient> {
    @InjectMocks
    ServerDeviceClientParserImpl parser;

    @Mock
    VersionErrorParser versionErrorParser;
    @Mock
    GetVersionResultParser getVersionResultParser;
    @Mock
    SetActivityTimeoutResultParser setActivityTimeoutResultParser;
    @Mock
    PingServerResultClientParser pingServerResultClientParser;

    public ServerDeviceClientParserParserImplTest(final Class<ServerDeviceClient> protoToTestClass,
                                                  final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
            {SuplaGetVersionResult.class, getDeclaredField(ServerDeviceClientParserParserImplTest.class,
                "getVersionResultParser")},
            {SuplaPingServerResultClient.class,
                getDeclaredField(ServerDeviceClientParserParserImplTest.class,
                    "pingServerResultClientParser")},
            {SuplaSetActivityTimeoutResult.class,
                getDeclaredField(ServerDeviceClientParserParserImplTest.class,
                    "setActivityTimeoutResultParser")},
            {SuplaVersionError.class, getDeclaredField(ServerDeviceClientParserParserImplTest.class,
                "versionErrorParser")},

        });
    }

    @Override
    protected Parser<ServerDeviceClientEntity, ServerDeviceClient> getParser() {
        return parser;
    }

    @Override
    protected Class<ServerDeviceClient> suplaClass() {
        return ServerDeviceClient.class;
    }
}