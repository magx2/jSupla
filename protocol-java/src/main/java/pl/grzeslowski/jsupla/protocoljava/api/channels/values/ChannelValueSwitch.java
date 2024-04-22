package pl.grzeslowski.jsupla.protocoljava.api.channels.values;

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
        } else if (channelValue instanceof OnOff) {
            return callback.onOnOff((OnOff) channelValue);
        } else if (channelValue instanceof OpenClose) {
            return callback.onOpenClose((OpenClose) channelValue);
        } else if (channelValue instanceof PercentValue) {
            return callback.onPercentValue((PercentValue) channelValue);
        } else if (channelValue instanceof RgbValue) {
            return callback.onRgbValue((RgbValue) channelValue);
        } else if (channelValue instanceof StoppableOpenClose) {
            return callback.onStoppableOpenClose((StoppableOpenClose) channelValue);
        } else if (channelValue instanceof TemperatureAndHumidityValue) {
            return callback.onTemperatureAndHumidityValue((TemperatureAndHumidityValue) channelValue);
        } else if (channelValue instanceof TemperatureValue) {
            return callback.onTemperatureValue((TemperatureValue) channelValue);
        } else if (channelValue instanceof UnknownValue) {
            return callback.onUnknownValue((UnknownValue) channelValue);
        } else {
            throw new IllegalArgumentException(format("Don't know where to dispatch channels value with class %s! " +
                    "This should NEVER occur on production!",
                channelValue.getClass().getSimpleName()));
        }
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

        T onUnknownValue(UnknownValue unknownValue);
    }
}
