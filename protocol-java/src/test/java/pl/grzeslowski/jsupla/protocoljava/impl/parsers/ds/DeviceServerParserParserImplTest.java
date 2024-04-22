package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.*;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ForInterfaceParserTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DeviceServerParserParserImplTest extends ForInterfaceParserTest<DeviceServerEntity, DeviceServer> {
    @InjectMocks
    DeviceServerParserImpl parser;

    @Mock
    ChannelNewValueResultParser channelNewValueResultParser;
    @Mock
    DeviceChannelValueParser deviceChannelValueParser;
    @Mock
    RegisterDeviceParser registerDeviceParser;
    @Mock
    DeviceChannelParser deviceChannelParser;
    @Mock
    DeviceChannelBParser deviceChannelBParser;
    @Mock
    FirmwareUpdateParamsParser firmwareUpdateParamsParser;
    @Mock
    RegisterDeviceBParser registerDeviceBParser;
    @Mock
    RegisterDeviceCParser registerDeviceCParser;

    public DeviceServerParserParserImplTest(final Class<DeviceServer> protoToTestClass,
                                            final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
            {SuplaChannelNewValueResult.class,
                getDeclaredField(DeviceServerParserParserImplTest.class, "channelNewValueResultParser")},
            {SuplaDeviceChannelValue.class,
                getDeclaredField(DeviceServerParserParserImplTest.class, "deviceChannelValueParser")},
            {SuplaFirmwareUpdateParams.class,
                getDeclaredField(DeviceServerParserParserImplTest.class, "firmwareUpdateParamsParser")},
            {SuplaRegisterDevice.class,
                getDeclaredField(DeviceServerParserParserImplTest.class, "registerDeviceParser")},
            {SuplaRegisterDeviceB.class,
                getDeclaredField(DeviceServerParserParserImplTest.class, "registerDeviceBParser")},
            {SuplaRegisterDeviceC.class,
                getDeclaredField(DeviceServerParserParserImplTest.class, "registerDeviceCParser")}
        });
    }

    @Override
    protected Parser<DeviceServerEntity, DeviceServer> getParser() {
        return parser;
    }

    @Override
    protected Class<DeviceServer> suplaClass() {
        return DeviceServer.class;
    }
}