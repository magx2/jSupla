package pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ForInterfaceParserTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DeviceClientServerParserEntityParserImplTest
        extends ForInterfaceParserTest<DeviceClientServerEntity, DeviceClientServer> {
    @InjectMocks DeviceClientServerParserImpl parser;

    @Mock PingServerParser pingServerParser;
    @Mock SetActivityTimeoutParser setActivityTimeoutParser;

    public DeviceClientServerParserEntityParserImplTest(final Class<DeviceClientServer> protoToTestClass,
                                                        final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaPingServer.class,
                        getDeclaredField(DeviceClientServerParserEntityParserImplTest.class, "pingServerParser")},
                {SuplaSetActivityTimeout.class,
                        getDeclaredField(DeviceClientServerParserEntityParserImplTest.class,
                                "setActivityTimeoutParser")}
        });
    }

    @Override
    protected Parser<DeviceClientServerEntity, DeviceClientServer> getParser() {
        return parser;
    }

    @Override
    protected Class<DeviceClientServer> suplaClass() {
        return DeviceClientServer.class;
    }
}