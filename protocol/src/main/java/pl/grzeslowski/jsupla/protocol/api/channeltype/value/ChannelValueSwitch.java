package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.util.Objects.requireNonNull;

public final class ChannelValueSwitch<T> {
    private final Callback<T> callback;

    public ChannelValueSwitch(final Callback<T> callback) {
        this.callback = requireNonNull(callback);
    }

    public T doSwitch(ChannelValue channelValue) {
        return switch (channelValue) {
            case DecimalValue decimalValue -> callback.onDecimalValue(decimalValue);
            case ElectricityMeterValue electricityMeterValue ->
                    callback.onElectricityMeter(electricityMeterValue);
            case HvacValue hvacValue -> callback.onHvacValue(hvacValue);
            case OnOff onOff -> callback.onOnOff(onOff);
            case OpenClose openClose -> callback.onOpenClose(openClose);
            case PercentValue percentValue -> callback.onPercentValue(percentValue);
            case RgbValue rgbValue -> callback.onRgbValue(rgbValue);
            case StoppableOpenClose stoppableOpenClose ->
                    callback.onStoppableOpenClose(stoppableOpenClose);
            case TemperatureAndHumidityValue temperatureAndHumidityValue ->
                    callback.onTemperatureAndHumidityValue(temperatureAndHumidityValue);
            case TemperatureValue temperatureValue -> callback.onTemperatureValue(temperatureValue);
            case HumidityValue humidityValue -> callback.onHumidityValue(humidityValue);
            case TimerValue timerValue -> callback.onTimerValue(timerValue);
            case ActionTrigger actionTrigger -> callback.onActionTrigger(actionTrigger);
            case UnknownValue unknownValue -> callback.onUnknownValue(unknownValue);
        };
    }

    public interface Callback<T> {
        T onDecimalValue(DecimalValue decimalValue);

        T onOnOff(OnOff onOff);

        T onOpenClose(OpenClose openClose);

        T onPercentValue(PercentValue percentValue);

        T onRgbValue(RgbValue rgbValue);

        T onStoppableOpenClose(StoppableOpenClose stoppableOpenClose);

        T onTemperatureValue(TemperatureValue temperatureValue);
        
        T onHumidityValue(HumidityValue humidityValue);

        T onTemperatureAndHumidityValue(TemperatureAndHumidityValue temperatureAndHumidityValue);

        T onElectricityMeter(ElectricityMeterValue electricityMeterValue);

        T onHvacValue(HvacValue channelValue);

        T onTimerValue(TimerValue channelValue);

        T onActionTrigger(ActionTrigger actionTriggerValue);

        T onUnknownValue(UnknownValue unknownValue);
    }
}
