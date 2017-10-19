package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ColorTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.RelayTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.StoppableOpenCloseEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ThermometerTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValueSwitch;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.DecimalValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OpenClose;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.PercentValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.StoppableOpenClose;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.UnknownValue;

import static java.util.Objects.requireNonNull;

public class ChannelTypeEncoderImpl implements ChannelTypeEncoder {
    private final ColorTypeChannelEncoder colorTypeChannelEncoder;
    private final RelayTypeChannelEncoder relayTypeChannelEncoder;
    private final ThermometerTypeChannelEncoder thermometerTypeChannelEncoder;
    private final StoppableOpenCloseEncoder stoppableOpenCloseEncoder;

    private ChannelValueSwitch.Callback<byte[]> callback = new ChannelValueSwitch.Callback<byte[]>() {
        @Override
        public byte[] onDecimalValue(final DecimalValue decimalValue) {
            throw new UnsupportedOperationException("ChannelTypeEncoderImpl.onDecimalValue(decimalValue)");
        }

        @Override
        public byte[] onOnOff(final OnOff onOff) {
            return relayTypeChannelEncoder.encode(onOff);
        }

        @Override
        public byte[] onOpenClose(final OpenClose openClose) {
            throw new UnsupportedOperationException("ChannelTypeEncoderImpl.onOpenClose(openClose)");
        }

        @Override
        public byte[] onPercentValue(final PercentValue percentValue) {
            throw new UnsupportedOperationException("ChannelTypeEncoderImpl.onPercentValue(percentValue)");
        }

        @Override
        public byte[] onRgbValue(final RgbValue rgbValue) {
            return colorTypeChannelEncoder.encode(rgbValue);
        }

        @Override
        public byte[] onStoppableOpenClose(final StoppableOpenClose stoppableOpenClose) {
            return stoppableOpenCloseEncoder.encode(stoppableOpenClose);
        }

        @Override
        public byte[] onTemperatureValue(final TemperatureValue temperatureValue) {
            return thermometerTypeChannelEncoder.encode(temperatureValue);
        }

        @Override
        public byte[] onTemperatureAndHumidityValue(final TemperatureAndHumidityValue temperatureAndHumidityValue) {
            return thermometerTypeChannelEncoder.encode(temperatureAndHumidityValue);
        }

        @Override
        public byte[] onUnknownValue(final UnknownValue unknownValue) {
            throw new UnsupportedOperationException("ChannelTypeEncoderImpl.onUnknownValue(unknownValue)");
        }
    };

    public ChannelTypeEncoderImpl(final ColorTypeChannelEncoder colorTypeChannelEncoder,
                                  final RelayTypeChannelEncoder relayTypeChannelEncoder,
                                  final ThermometerTypeChannelEncoder thermometerTypeChannelEncoder,
                                  final StoppableOpenCloseEncoder stoppableOpenCloseEncoder) {
        this.colorTypeChannelEncoder = requireNonNull(colorTypeChannelEncoder);
        this.relayTypeChannelEncoder = requireNonNull(relayTypeChannelEncoder);
        this.thermometerTypeChannelEncoder = requireNonNull(thermometerTypeChannelEncoder);
        this.stoppableOpenCloseEncoder = requireNonNull(stoppableOpenCloseEncoder);
    }

    @Override
    public byte[] encode(final ChannelValue channelValue) {
        return new ChannelValueSwitch<>(callback).doSwitch(channelValue);
    }
}
