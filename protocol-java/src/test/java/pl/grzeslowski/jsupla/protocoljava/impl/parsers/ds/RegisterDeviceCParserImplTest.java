package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class RegisterDeviceCParserImplTest extends AbstractParserTest<RegisterDeviceC, SuplaRegisterDeviceC> {
    @InjectMocks RegisterDeviceCParserImpl parser;
    @Mock StringParser stringParser;
    @Mock DeviceChannelBParser deviceChannelBParser;

    @Override
    protected SuplaRegisterDeviceC given() {
        SuplaRegisterDeviceC given = super.given();

        givenStringParser(stringParser);
        BDDMockito.given(deviceChannelBParser.parse(any()))
                .willAnswer(__ -> RANDOM_ENTITY.nextObject(RegisterDeviceC.class));

        return given;
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void then(final RegisterDeviceC entity, final SuplaRegisterDeviceC supla) {
        assertThat(entity.getLocationId()).isEqualTo(supla.locationId);
        verify(stringParser).parsePassword(supla.locationPwd);
        verify(stringParser).parse(supla.guid);
        verify(stringParser).parse(supla.name);
        verify(stringParser).parse(supla.softVer);
        verify(stringParser).parse(supla.serverName);
        assertThat(entity.getChannelCount()).isEqualTo(supla.channelCount);
        Arrays.stream(supla.channels)
                .forEach(channel -> verify(deviceChannelBParser).parse(channel));
        verifyNoMoreInteractions(deviceChannelBParser);
    }

    @Override
    protected Parser<RegisterDeviceC, SuplaRegisterDeviceC> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterDeviceC> classToTest() {
        return SuplaRegisterDeviceC.class;
    }
}