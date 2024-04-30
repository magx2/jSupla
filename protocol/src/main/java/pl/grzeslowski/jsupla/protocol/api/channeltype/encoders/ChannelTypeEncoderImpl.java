package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;


import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

@RequiredArgsConstructor
public class ChannelTypeEncoderImpl {
    public static final ChannelTypeEncoderImpl INSTANCE = new ChannelTypeEncoderImpl(
        new ColorTypeChannelEncoderImpl(),
        new RelayTypeChannelEncoderImpl(),
        new ThermometerTypeChannelEncoderImpl(),
        new StoppableOpenCloseEncoderImpl()
    );
    private final ColorTypeChannelEncoderImpl colorTypeChannelEncoder;
    private final RelayTypeChannelEncoderImpl relayTypeChannelEncoder;
    private final ThermometerTypeChannelEncoderImpl thermometerTypeChannelEncoder;
    private final StoppableOpenCloseEncoderImpl stoppableOpenCloseEncoder;

    private final ChannelValueSwitch.Callback<byte[]> callback = new ChannelValueSwitch.Callback<byte[]>() {
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

    public byte[] encode(final ChannelValue channelValue) {
        return new ChannelValueSwitch<>(callback).doSwitch(channelValue);
    }
}
