package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class RegisterDeviceDParserImplTest extends AbstractParserTest<RegisterDeviceD, SuplaRegisterDeviceD> {
    @InjectMocks
    RegisterDeviceDParserImpl parser;
    @Mock
    DeviceChannelBParser deviceChannelBParser;
    @Mock
    StringParser stringParser;

    @Before
    public void setUp() {
        givenStringParser(stringParser);
    }

    @Override
    protected void then(RegisterDeviceD entity, SuplaRegisterDeviceD supla) {
        verify(stringParser).parse(supla.email);
        verify(stringParser).parse(supla.authKey);
        verify(stringParser).parse(supla.guid);
        verify(stringParser).parse(supla.name);
        verify(stringParser).parse(supla.softVer);
        verify(stringParser).parse(supla.serverName);
        assertThat(entity.getChannels()).hasSize(supla.channelCount);
        for (SuplaDeviceChannelB channel : supla.channels) {
            verify(deviceChannelBParser).parse(channel);
        }
    }

    @Override
    protected Parser<RegisterDeviceD, SuplaRegisterDeviceD> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterDeviceD> classToTest() {
        return SuplaRegisterDeviceD.class;
    }
}