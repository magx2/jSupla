package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelValueParserImplTest extends ParserTest<DeviceChannelValue, SuplaDeviceChannelValue> {
    @InjectMocks DeviceChannelValueParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;

    @Override
    protected SuplaDeviceChannelValue given() {
        final SuplaDeviceChannelValue supla = super.given();
        BDDMockito.given(channelTypeDecoder.decode(supla)).willReturn(new ChannelValue() {
        });
        return supla;
    }

    @Override
    protected void then(final DeviceChannelValue entity, final SuplaDeviceChannelValue supla) {
        assertThat(entity.getChannelNumber()).isEqualTo(supla.channelNumber);
        verify(channelTypeDecoder).decode(supla);
    }

    @Override
    protected Parser<DeviceChannelValue, SuplaDeviceChannelValue> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaDeviceChannelValue> classToTest() {
        return SuplaDeviceChannelValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new DeviceChannelValueParserImpl(null);
    }
}