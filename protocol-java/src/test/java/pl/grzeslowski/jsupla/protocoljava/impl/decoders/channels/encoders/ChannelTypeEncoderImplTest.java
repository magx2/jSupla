package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ColorTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.RelayTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.StoppableOpenCloseEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ThermometerTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.StoppableOpenClose;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureValue;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class ChannelTypeEncoderImplTest {
    @InjectMocks ChannelTypeEncoderImpl encoder;

    @Mock ColorTypeChannelEncoder colorTypeChannelEncoder;
    @Mock RelayTypeChannelEncoder relayTypeChannelEncoder;
    @Mock ThermometerTypeChannelEncoder thermometerTypeChannelEncoder;
    @Mock StoppableOpenCloseEncoder stoppableOpenCloseEncoder;

    @Test
    public void shouldInvokeColorTypeChannelEncoder() throws Exception {

        // given
        final RgbValue channelValue = RANDOM_ENTITY.nextObject(RgbValue.class);

        // when
        encoder.encode(channelValue);

        // then
        verify(colorTypeChannelEncoder).encode(channelValue);
    }

    @Test
    public void shouldInvokeRelayTypeChannelEncoder() throws Exception {

        // given
        final OnOff channelValue = RANDOM_ENTITY.nextObject(OnOff.class);

        // when
        encoder.encode(channelValue);

        // then
        verify(relayTypeChannelEncoder).encode(channelValue);
    }

    @Test
    public void shouldInvokeThermometerTypeChannelEncoder() throws Exception {

        // given
        final TemperatureValue channelValue = RANDOM_ENTITY.nextObject(TemperatureValue.class);

        // when
        encoder.encode(channelValue);

        // then
        verify(thermometerTypeChannelEncoder).encode(channelValue);
    }

    @Test
    public void shouldInvokeThermometerTypeChannelEncoderForHumidity() throws Exception {

        // given
        final TemperatureAndHumidityValue channelValue = RANDOM_ENTITY.nextObject(TemperatureAndHumidityValue.class);

        // when
        encoder.encode(channelValue);

        // then
        verify(thermometerTypeChannelEncoder).encode(channelValue);
    }

    @Test
    public void shouldInvokeStoppableOpenCloseEncoder() throws Exception {

        // given
        final StoppableOpenClose channelValue = RANDOM_ENTITY.nextObject(StoppableOpenClose.class);

        // when
        encoder.encode(channelValue);

        // then
        verify(stoppableOpenCloseEncoder).encode(channelValue);
    }
}