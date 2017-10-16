package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
@Deprecated
public class DeviceChannelParserImplTest extends ParserTest<DeviceChannel, SuplaDeviceChannel> {
    @InjectMocks DeviceChannelParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;

    @Override
    protected SuplaDeviceChannel given() {
        final SuplaDeviceChannel supla = super.given();
        BDDMockito.given(channelTypeDecoder.decode(supla)).willReturn(new ChannelValue() {
        });
        return supla;
    }

    @Override
    protected void then(final DeviceChannel entity, final SuplaDeviceChannel supla) {
        assertThat(entity.getNumber()).isEqualTo(supla.number);
        assertThat(entity.getType()).isEqualTo(supla.type);
        verify(channelTypeDecoder).decode(supla);
    }

    @Override
    protected Parser<DeviceChannel, SuplaDeviceChannel> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaDeviceChannel> classToTest() {
        return SuplaDeviceChannel.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new DeviceChannelParserImpl(null);
    }
}