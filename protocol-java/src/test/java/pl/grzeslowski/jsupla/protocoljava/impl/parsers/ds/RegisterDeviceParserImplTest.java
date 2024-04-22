package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class RegisterDeviceParserImplTest extends AbstractParserTest<RegisterDevice, SuplaRegisterDevice> {
    @InjectMocks
    RegisterDeviceParserImpl parser;
    @Mock
    StringParser stringParser;
    @Mock
    DeviceChannelParser deviceChannelParser;

    @Override
    protected SuplaRegisterDevice given() {
        final SuplaRegisterDevice supla = super.given();

        givenStringParser(stringParser);
        BDDMockito.given(deviceChannelParser.parse(any()))
            .willAnswer(__ -> RANDOM_ENTITY.nextObject(DeviceChannel.class));

        return supla;
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void then(final RegisterDevice entity, final SuplaRegisterDevice supla) {
        assertThat(entity.getLocationId()).isEqualTo(supla.locationId);
        verify(stringParser).parsePassword(supla.locationPwd);
        verify(stringParser).parseHexString(supla.guid);
        verify(stringParser).parse(supla.name);
        verify(stringParser).parse(supla.softVer);
        assertThat(entity.getChannels().size()).isEqualTo(supla.channelCount);
        Arrays.stream(supla.channels)
            .forEach(channel -> verify(deviceChannelParser).parse(channel));
        verifyNoMoreInteractions(deviceChannelParser);
    }

    @Override
    protected Parser<RegisterDevice, SuplaRegisterDevice> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterDevice> classToTest() {
        return SuplaRegisterDevice.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new RegisterDeviceParserImpl(null, deviceChannelParser);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenDeviceChannelParserIsNull() {
        new RegisterDeviceParserImpl(stringParser, null);
    }
}