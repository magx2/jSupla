package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.sdc;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.GetVersionResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.PingServerResultClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.SetActivityTimeoutResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.VersionErrorParser;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.FactoryTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ServerDeviceClientParserFactoryImplTest extends FactoryTest<ServerDeviceClientEntity, ServerDeviceClient> {
    @InjectMocks ServerDeviceClientParserFactoryImpl factory;

    @Mock VersionErrorParser versionErrorParser;
    @Mock GetVersionResultParser getVersionResultParser;
    @Mock SetActivityTimeoutResultParser setActivityTimeoutResultParser;
    @Mock PingServerResultClientParser pingServerResultClientParser;

    public ServerDeviceClientParserFactoryImplTest(final Class<ServerDeviceClient> protoToTestClass,
                                                   final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaGetVersionResult.class, getDeclaredField(ServerDeviceClientParserFactoryImplTest.class,
                        "getVersionResultParser")},
                {SuplaPingServerResultClient.class, getDeclaredField(ServerDeviceClientParserFactoryImplTest.class,
                        "pingServerResultClientParser")},
                {SuplaSetActivityTimeoutResult.class, getDeclaredField(ServerDeviceClientParserFactoryImplTest.class,
                        "setActivityTimeoutResultParser")},
                {SuplaVersionError.class, getDeclaredField(ServerDeviceClientParserFactoryImplTest.class,
                        "versionErrorParser")},

        });
    }

    @Override
    protected ParserFactory<ServerDeviceClientEntity, ServerDeviceClient> parserFactory() {
        return factory;
    }

    @Override
    protected Class<ServerDeviceClient> suplaClass() {
        return ServerDeviceClient.class;
    }
}