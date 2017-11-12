package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
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
import pl.grzeslowski.jsupla.protocoljava.common.TestUtil;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class ParserFactoryImplTestForCommonClasses {
    @InjectMocks ParserImpl parser;

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

    final Class<? extends Proto> clazzToTest;
    final String parserName;
    Parser<?, ? super Proto> mockParser;

    public ParserFactoryImplTestForCommonClasses(final Class<? extends Proto> clazzToTest, String parserName) {
        this.clazzToTest = clazzToTest;
        this.parserName = parserName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {SuplaDeviceChannel.class, "deviceChannelParser"},
                {SuplaDeviceChannelB.class, "deviceChannelBParser"},
                {SuplaFirmwareUpdateUrl.class, "firmwareUpdateUrlParser"},
                {SuplaChannelValue.class, "channelValueParser"},
                {SuplaTimeval.class, "timevalParser"}
        });
    }

    @SuppressWarnings("unchecked")
    @Before
    public void init() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        mockParser = (Parser<?, ? super Proto>) TestUtil.getDeclaredField(this.getClass(), parserName).get(this);
    }

    @Test
    public void shouldUseProperParser() throws Exception {

        // given
        final Proto proto = RANDOM_SUPLA.nextObject(clazzToTest);

        // when
        parser.parse(proto);

        // then
        verify(mockParser).parse(proto);
    }
}