package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.ds;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.ChannelNewValueResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.FirmwareUpdateParamsParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceCParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceParser;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.FactoryTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DeviceServerParserFactoryImplTest extends FactoryTest<DeviceServerEntity, DeviceServer> {
    @InjectMocks DeviceServerParserFactoryImpl factory;

    @Mock ChannelNewValueResultParser channelNewValueResultParser;
    @Mock DeviceChannelValueParser deviceChannelValueParser;
    @Mock RegisterDeviceParser registerDeviceParser;
    @Mock DeviceChannelParser deviceChannelParser;
    @Mock DeviceChannelBParser deviceChannelBParser;
    @Mock FirmwareUpdateParamsParser firmwareUpdateParamsParser;
    @Mock RegisterDeviceBParser registerDeviceBParser;
    @Mock RegisterDeviceCParser registerDeviceCParser;

    public DeviceServerParserFactoryImplTest(final Class<DeviceServer> protoToTestClass, final Field resultField) {
        super(protoToTestClass, resultField);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {SuplaChannelNewValueResult.class,
                        getDeclaredField(DeviceServerParserFactoryImplTest.class, "channelNewValueResultParser")},
                {SuplaDeviceChannelValue.class,
                        getDeclaredField(DeviceServerParserFactoryImplTest.class, "deviceChannelValueParser")},
                {SuplaFirmwareUpdateParams.class,
                        getDeclaredField(DeviceServerParserFactoryImplTest.class, "firmwareUpdateParamsParser")},
                {SuplaRegisterDevice.class,
                        getDeclaredField(DeviceServerParserFactoryImplTest.class, "registerDeviceParser")},
                {SuplaRegisterDeviceB.class,
                        getDeclaredField(DeviceServerParserFactoryImplTest.class, "registerDeviceBParser")},
                {SuplaRegisterDeviceC.class,
                        getDeclaredField(DeviceServerParserFactoryImplTest.class, "registerDeviceCParser")}
        });
    }

    @Override
    protected ParserFactory<DeviceServerEntity, DeviceServer> parserFactory() {
        return factory;
    }

    @Override
    protected Class<DeviceServer> suplaClass() {
        return DeviceServer.class;
    }
}