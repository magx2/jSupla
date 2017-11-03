package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.cs.ClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.dcs.DeviceClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ds.DeviceServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sc.ServerClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sd.ServerDeviceParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sdc.ServerDeviceClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class ParserFactoryImplNullPointerTest {
    @Mock ClientServerParserFactory clientServerParserFactory;
    @Mock DeviceClientServerParserFactory deviceClientServerParserFactory;
    @Mock DeviceServerParserFactory deviceServerParserFactory;
    @Mock ServerClientParserFactory serverClientParserFactory;
    @Mock ServerDeviceParserFactory serverDeviceParserFactory;
    @Mock ServerDeviceClientParserFactory serverDeviceClientParserFactory;

    @Mock DeviceChannelParser deviceChannelParser;
    @Mock DeviceChannelBParser deviceChannelBParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock ChannelValueParser channelValueParser;
    @Mock TimevalParser timevalParser;

    final Class<?> dependencyClass;

    public ParserFactoryImplNullPointerTest(final Class<?> dependencyClass) {
        this.dependencyClass = dependencyClass;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ClientServerParserFactory.class},
                {DeviceClientServerParserFactory.class},
                {DeviceServerParserFactory.class},
                {ServerClientParserFactory.class},
                {ServerDeviceParserFactory.class},
                {ServerDeviceClientParserFactory.class},
                {DeviceChannelParser.class},
                {DeviceChannelBParser.class},
                {FirmwareUpdateUrlParser.class},
                {ChannelValueParser.class},
                {TimevalParser.class}
        });
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenDependencyIsNullIsNull() {
        final ClientServerParserFactory clientServerParserFactory = objectOrNull(this.clientServerParserFactory);
        final DeviceClientServerParserFactory deviceClientServerParserFactory =
                objectOrNull(this.deviceClientServerParserFactory);
        final DeviceServerParserFactory deviceServerParserFactory = objectOrNull(this.deviceServerParserFactory);
        final ServerClientParserFactory serverClientParserFactory = objectOrNull(this.serverClientParserFactory);
        final ServerDeviceParserFactory serverDeviceParserFactory = objectOrNull(this.serverDeviceParserFactory);
        final ServerDeviceClientParserFactory serverDeviceClientParserFactory =
                objectOrNull(this.serverDeviceClientParserFactory);

        final DeviceChannelParser deviceChannelParser = objectOrNull(this.deviceChannelParser);
        final DeviceChannelBParser deviceChannelBParser = objectOrNull(this.deviceChannelBParser);
        final FirmwareUpdateUrlParser firmwareUpdateUrlParser = objectOrNull(this.firmwareUpdateUrlParser);
        final ChannelValueParser channelValueParser = objectOrNull(this.channelValueParser);
        final TimevalParser timevalParser = objectOrNull(this.timevalParser);

        new ParserFactoryImpl(clientServerParserFactory,
                                     deviceClientServerParserFactory,
                                     deviceServerParserFactory,
                                     serverClientParserFactory,
                                     serverDeviceParserFactory,
                                     serverDeviceClientParserFactory,
                                     deviceChannelParser,
                                     deviceChannelBParser,
                                     firmwareUpdateUrlParser,
                                     channelValueParser,
                                     timevalParser);
    }

    private <T> T objectOrNull(T object) {
        if (!dependencyClass.isInstance(object)) {
            return object;
        } else {
            return null;
        }
    }

}