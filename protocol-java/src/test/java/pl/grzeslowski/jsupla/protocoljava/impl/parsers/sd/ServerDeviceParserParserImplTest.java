package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ForInterfaceParserTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ServerDeviceParserParserImplTest extends ForInterfaceParserTest<ServerDeviceEntity, ServerDevice> {
    @InjectMocks ServerDeviceParserImpl parser;

    @Mock ChannelNewValueParser channelNewValueParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock FirmwareUpdateUrlResultParser firmwareUpdateUrlResultParser;
    @Mock RegisterDeviceResultParser registerDeviceResultParser;

    public ServerDeviceParserParserImplTest(final Class<ServerDevice> protoToTestClass,
                                            final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaChannelNewValue.class,
                        getDeclaredField(ServerDeviceParserParserImplTest.class,
                                "channelNewValueParser")},
                {SuplaFirmwareUpdateUrlResult.class,
                        getDeclaredField(ServerDeviceParserParserImplTest.class,
                                "firmwareUpdateUrlResultParser")},
                {SuplaRegisterDeviceResult.class,
                        getDeclaredField(ServerDeviceParserParserImplTest.class,
                                "registerDeviceResultParser")}
        });
    }

    @Override
    protected Parser<ServerDeviceEntity, ServerDevice> getParser() {
        return parser;
    }

    @Override
    protected Class<ServerDevice> suplaClass() {
        return ServerDevice.class;
    }
}