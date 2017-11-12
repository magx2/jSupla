package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import com.google.common.reflect.ClassPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ServerClientEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.ServerDeviceClientEntityParser;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
@RunWith(Parameterized.class)
public class ParserFactoryImplForAllProtoTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        return ClassPath.from(Proto.class.getClassLoader())
                       .getTopLevelClassesRecursive(SuplaDataPacket.class.getPackage().getName())
                       .asList()
                       .stream()
                       .map(ClassPath.ClassInfo::load)
                       .filter(clazz -> !clazz.isInterface())
                       .filter(Proto.class::isAssignableFrom)
                       .filter(clazz -> !SuplaDataPacket.class.equals(clazz))
                       .map(clazz -> (Class<Proto>) clazz)
                       .map(clazz -> RANDOM_SUPLA.nextObject(clazz))
                       .map(proto -> new Object[]{proto})
                       .collect(Collectors.toList());
    }

    final Proto proto;

    @InjectMocks ParserImpl factory;

    @Mock ClientServerEntityParser clientServerParserFactory;
    @Mock DeviceClientServerEntityParser deviceClientServerParserFactory;
    @Mock DeviceServerEntityParser deviceServerParserFactory;
    @Mock ServerClientEntityParser serverClientParserFactory;
    @Mock ServerDeviceEntityParser serverDeviceParserFactory;
    @Mock ServerDeviceClientEntityParser serverDeviceClientParserFactory;

    @Mock DeviceChannelParser deviceChannelParser;
    @Mock DeviceChannelBParser deviceChannelBParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock ChannelValueParser channelValueParser;
    @Mock TimevalParser timevalParser;

    public ParserFactoryImplForAllProtoTest(final Proto proto) {
        this.proto = proto;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldWorkForSuplaProtoSubClass() {
        factory.parse(proto);
    }
}
