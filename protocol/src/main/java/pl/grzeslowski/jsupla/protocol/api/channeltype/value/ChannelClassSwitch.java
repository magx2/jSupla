package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ChannelClassSwitch<T> {
    private final Callback<T> callback;

    public ChannelClassSwitch(final Callback<T> callback) {
        this.callback = requireNonNull(callback);
    }

    public T doSwitch(Class<? extends ChannelValue> channelClass) {
        if (channelClass.isAssignableFrom(OnOffValue.class)) {
            return callback.onOnOff();
        }
        if (channelClass.isAssignableFrom(PercentValue.class)) {
            return callback.onPercentValue();
        }
        if (channelClass.isAssignableFrom(RgbValue.class)) {
            return callback.onRgbValue();
        }
        if (channelClass.isAssignableFrom(TemperatureAndHumidityValue.class)) {
            return callback.onTemperatureAndHumidityValue();
        }
        if (channelClass.isAssignableFrom(TemperatureDoubleValue.class)) {
            return callback.onTemperatureDoubleValue();
        }
        if (channelClass.isAssignableFrom(HumidityValue.class)) {
            return callback.onHumidityValue();
        }
        if (channelClass.isAssignableFrom(ElectricityMeterValue.class)) {
            return callback.onElectricityMeter();
        }
        if (channelClass.isAssignableFrom(HvacValue.class)) {
            return callback.onHvacValue();
        }
        if (channelClass.isAssignableFrom(TimerValue.class)) {
            return callback.onTimerValue();
        }
        if (channelClass.isAssignableFrom(PressureValue.class)) {
            return callback.onPressureValue();
        }
        if (channelClass.isAssignableFrom(RainValue.class)) {
            return callback.onRainValue();
        }
        if (channelClass.isAssignableFrom(WeightValue.class)) {
            return callback.onWeightValue();
        }
        if (channelClass.isAssignableFrom(WindValue.class)) {
            return callback.onWindValue();
        }
        if (channelClass.isAssignableFrom(HeatpolThermostatValue.class)) {
            return callback.onHeatpolThermostatValue();
        }
        if (channelClass.isAssignableFrom(UnknownValue.class)) {
            return callback.onUnknownValue();
        }

        throw new IllegalArgumentException(
                format(
                        "Don't know where to dispatch channels value with class %s! "
                                + "This should NEVER occur on production!",
                        channelClass.getSimpleName()));
    }

    @SuppressWarnings("UnusedReturnValue")
    public interface Callback<T> {
        T onOnOff();

        T onPercentValue();

        T onRgbValue();

        T onTemperatureDoubleValue();

        T onHumidityValue();

        T onTemperatureAndHumidityValue();

        T onElectricityMeter();

        T onHvacValue();

        T onTimerValue();

        T onPressureValue();

        T onRainValue();

        T onWeightValue();

        T onWindValue();

        T onHeatpolThermostatValue();

        T onUnknownValue();
    }
}
