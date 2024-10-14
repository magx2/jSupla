package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ChannelClassSwitch<T> {
    private final Callback<T> callback;

    public ChannelClassSwitch(final Callback<T> callback) {
        this.callback = requireNonNull(callback);
    }

    public T doSwitch(Class<? extends ChannelValue> channelClass) {
        if (channelClass.isAssignableFrom(DecimalValue.class)) {
            return callback.onDecimalValue();
        }
        if (channelClass.isAssignableFrom(OnOff.class)) {
            return callback.onOnOff();
        }
        if (channelClass.isAssignableFrom(OpenClose.class)) {
            return callback.onOpenClose();
        }
        if (channelClass.isAssignableFrom(PercentValue.class)) {
            return callback.onPercentValue();
        }
        if (channelClass.isAssignableFrom(RgbValue.class)) {
            return callback.onRgbValue();
        }
        if (channelClass.isAssignableFrom(StoppableOpenClose.class)) {
            return callback.onStoppableOpenClose();
        }
        if (channelClass.isAssignableFrom(TemperatureAndHumidityValue.class)) {
            return callback.onTemperatureAndHumidityValue();
        }
        if (channelClass.isAssignableFrom(TemperatureValue.class)) {
            return callback.onTemperatureValue();
        }
        if (channelClass.isAssignableFrom(ElectricityMeterValue.class)) {
            return callback.onElectricityMeter();
        }
        if (channelClass.isAssignableFrom(HvacValue.class)) {
            return callback.onHvacValue();
        }
        if (channelClass.isAssignableFrom(UnknownValue.class)) {
            return callback.onUnknownValue();
        }

        throw new IllegalArgumentException(format("Don't know where to dispatch channels value with class %s! " +
                "This should NEVER occur on production!",
            channelClass.getSimpleName()));
    }

    @SuppressWarnings("UnusedReturnValue")
    public interface Callback<T> {
        T onDecimalValue();

        T onOnOff();

        T onOpenClose();

        T onPercentValue();

        T onRgbValue();

        T onStoppableOpenClose();

        T onTemperatureValue();

        T onTemperatureAndHumidityValue();

        T onElectricityMeter();

        T onHvacValue();

        T onUnknownValue();
    }
}
