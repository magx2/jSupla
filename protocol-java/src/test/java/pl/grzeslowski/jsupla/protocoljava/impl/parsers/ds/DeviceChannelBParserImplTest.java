package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelBParserImplTest extends ParserTest<DeviceChannelB, SuplaDeviceChannelB> {
    @InjectMocks DeviceChannelBParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;

    @Override
    protected SuplaDeviceChannelB given() {
        final SuplaDeviceChannelB supla = super.given();
        BDDMockito.given(channelTypeDecoder.decode(supla)).willReturn(new ChannelValue() {
        });
        return supla;
    }

    @Override
    protected void then(final DeviceChannelB entity, final SuplaDeviceChannelB supla) {
        assertThat(entity.getNumber()).isEqualTo(supla.number);
        assertThat(entity.getType()).isEqualTo(supla.type);
        assertThat(entity.getFunction()).isEqualTo(supla.funcList);
        assertThat(entity.getDefaultValue()).isEqualTo(supla.defaultValue);
        verify(channelTypeDecoder).decode(supla);
    }

    @Override
    protected Parser<DeviceChannelB, SuplaDeviceChannelB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaDeviceChannelB> classToTest() {
        return SuplaDeviceChannelB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new DeviceChannelBParserImpl(null);
    }
}