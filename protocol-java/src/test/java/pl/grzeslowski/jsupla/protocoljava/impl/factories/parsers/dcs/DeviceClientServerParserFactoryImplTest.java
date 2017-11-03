package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.dcs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.FactoryTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DeviceClientServerParserFactoryImplTest extends FactoryTest<DeviceClientServerEntity, DeviceClientServer> {
    @InjectMocks DeviceClientServerParserFactoryImpl factory;

    @Mock PingServerParser pingServerParser;
    @Mock SetActivityTimeoutParser setActivityTimeoutParser;

    public DeviceClientServerParserFactoryImplTest(final Class<DeviceClientServer> protoToTestClass,
                                                   final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaPingServer.class,
                        getDeclaredField(DeviceClientServerParserFactoryImplTest.class, "pingServerParser")},
                {SuplaSetActivityTimeout.class,
                        getDeclaredField(DeviceClientServerParserFactoryImplTest.class, "setActivityTimeoutParser")}
        });
    }

    @Override
    protected ParserFactory<DeviceClientServerEntity, DeviceClientServer> parserFactory() {
        return factory;
    }

    @Override
    protected Class<DeviceClientServer> suplaClass() {
        return DeviceClientServer.class;
    }
}