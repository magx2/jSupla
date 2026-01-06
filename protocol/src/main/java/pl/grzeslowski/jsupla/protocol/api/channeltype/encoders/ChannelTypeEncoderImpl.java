package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

@RequiredArgsConstructor
public class ChannelTypeEncoderImpl {
    public static final ChannelTypeEncoderImpl INSTANCE =
            new ChannelTypeEncoderImpl(
                    new ColorTypeChannelEncoderImpl(),
                    new RelayTypeChannelEncoderImpl(),
                    new ThermometerTypeChannelEncoderImpl(),
                    new HumidityTypeChannelEncoderImpl(),
                    new StoppableOpenCloseEncoderImpl(),
                    new ElectricityMeterEncoder(),
                    new HvacChannelEncoderImpl(),
                    new PercentageTypeEncoder());
    private final ColorTypeChannelEncoderImpl colorTypeChannelEncoder;
    private final RelayTypeChannelEncoderImpl relayTypeChannelEncoder;
    private final ThermometerTypeChannelEncoderImpl thermometerTypeChannelEncoder;
    private final HumidityTypeChannelEncoderImpl humidityTypeChannelEncoder;
    private final StoppableOpenCloseEncoderImpl stoppableOpenCloseEncoder;
    private final ElectricityMeterEncoder electricityMeterEncoder;
    private final HvacChannelEncoderImpl hvacChannelEncoderImpl;
    private final PercentageTypeEncoder percentageTypeEncoder;

    private final ChannelValueSwitch.Callback<byte[]> callback =
            new ChannelValueSwitch.Callback<>() {
                @Override
                public byte[] onDecimalValue(final DecimalValue decimalValue) {
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.onDecimalValue(decimalValue)");
                }

                @Override
                public byte[] onOnOff(final OnOff onOff) {
                    return relayTypeChannelEncoder.encode(onOff);
                }

                @Override
                public byte[] onOpenClose(final OpenClose openClose) {
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.onOpenClose(openClose)");
                }

                @Override
                public byte[] onPercentValue(final PercentValue percentValue) {
                    return percentageTypeEncoder.encode(percentValue);
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
                public byte[] onHumidityValue(HumidityValue humidityValue) {
                    return humidityTypeChannelEncoder.encode(humidityValue);
                }

                @Override
                public byte[] onTemperatureAndHumidityValue(
                        final TemperatureAndHumidityValue temperatureAndHumidityValue) {
                    return thermometerTypeChannelEncoder.encode(temperatureAndHumidityValue);
                }

                @Override
                public byte[] onElectricityMeter(ElectricityMeterValue electricityMeterValue) {
                    return electricityMeterEncoder.encode(electricityMeterValue);
                }

                @Override
                public byte[] onHvacValue(HvacValue channelValue) {
                    return hvacChannelEncoderImpl.encode(channelValue);
                }

                @Override
                public byte[] onTimerValue(TimerValue channelValue) {
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.onTimerValue(channelValue)");
                }

                @Override
                public byte[] onActionTrigger(ActionTrigger channelValue) {
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.onActionTrigger(channelValue)");
                }

                @Override
                public byte[] onUnknownValue(final UnknownValue unknownValue) {
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.onUnknownValue(unknownValue)");
                }
            };

    public byte[] encode(final ChannelValue channelValue) {
        return new ChannelValueSwitch<>(callback).doSwitch(channelValue);
    }
}
