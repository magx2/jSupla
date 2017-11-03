package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.sd;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.FactoryTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ServerDeviceParserFactoryImplTest extends FactoryTest<ServerDeviceEntity, ServerDevice> {
    @InjectMocks ServerDeviceParserFactoryImpl factory;

    @Mock ChannelNewValueParser channelNewValueParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock FirmwareUpdateUrlResultParser firmwareUpdateUrlResultParser;
    @Mock RegisterDeviceResultParser registerDeviceResultParser;

    public ServerDeviceParserFactoryImplTest(final Class<ServerDevice> protoToTestClass, final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaChannelNewValue.class,
                        getDeclaredField(ServerDeviceParserFactoryImplTest.class, "channelNewValueParser")},
                {SuplaFirmwareUpdateUrlResult.class,
                        getDeclaredField(ServerDeviceParserFactoryImplTest.class, "firmwareUpdateUrlResultParser")},
                {SuplaRegisterDeviceResult.class,
                        getDeclaredField(ServerDeviceParserFactoryImplTest.class, "registerDeviceResultParser")}
        });
    }

    @Override
    protected ParserFactory<ServerDeviceEntity, ServerDevice> parserFactory() {
        return factory;
    }

    @Override
    protected Class<ServerDevice> suplaClass() {
        return ServerDevice.class;
    }
}