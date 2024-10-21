package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ChannelValueSwitch<T> {
    private final Callback<T> callback;

    public ChannelValueSwitch(final Callback<T> callback) {
        this.callback = requireNonNull(callback);
    }

    public T doSwitch(ChannelValue channelValue) {
        if (channelValue instanceof DecimalValue) {
            return callback.onDecimalValue((DecimalValue) channelValue);
        }
        if (channelValue instanceof OnOff) {
            return callback.onOnOff((OnOff) channelValue);
        }
        if (channelValue instanceof OpenClose) {
            return callback.onOpenClose((OpenClose) channelValue);
        }
        if (channelValue instanceof PercentValue) {
            return callback.onPercentValue((PercentValue) channelValue);
        }
        if (channelValue instanceof RgbValue) {
            return callback.onRgbValue((RgbValue) channelValue);
        }
        if (channelValue instanceof StoppableOpenClose) {
            return callback.onStoppableOpenClose((StoppableOpenClose) channelValue);
        }
        if (channelValue instanceof TemperatureAndHumidityValue) {
            return callback.onTemperatureAndHumidityValue((TemperatureAndHumidityValue) channelValue);
        }
        if (channelValue instanceof TemperatureValue) {
            return callback.onTemperatureValue((TemperatureValue) channelValue);
        }
        if (channelValue instanceof ElectricityMeterValue) {
            return callback.onElectricityMeter((ElectricityMeterValue) channelValue);
        }
        if (channelValue instanceof HvacValue) {
            return callback.onHvacValue((HvacValue) channelValue);
        }
        if (channelValue instanceof TimerValue) {
            return callback.onTimerValue((TimerValue) channelValue);
        }
        if (channelValue instanceof UnknownValue) {
            return callback.onUnknownValue((UnknownValue) channelValue);
        }

        throw new IllegalArgumentException(format("Don't know where to dispatch channels value with class %s! " +
                "This should NEVER occur on production!",
            channelValue.getClass().getSimpleName()));
    }

    @SuppressWarnings("UnusedReturnValue")
    public interface Callback<T> {
        T onDecimalValue(DecimalValue decimalValue);

        T onOnOff(OnOff onOff);

        T onOpenClose(OpenClose openClose);

        T onPercentValue(PercentValue percentValue);

        T onRgbValue(RgbValue rgbValue);

        T onStoppableOpenClose(StoppableOpenClose stoppableOpenClose);

        T onTemperatureValue(TemperatureValue temperatureValue);

        T onTemperatureAndHumidityValue(TemperatureAndHumidityValue temperatureAndHumidityValue);

        T onElectricityMeter(ElectricityMeterValue electricityMeterValue);

        T onHvacValue(HvacValue channelValue);

        T onTimerValue(TimerValue channelValue);

        T onUnknownValue(UnknownValue unknownValue);
    }
}
