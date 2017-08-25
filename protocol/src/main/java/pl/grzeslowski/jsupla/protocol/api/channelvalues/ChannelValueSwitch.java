package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ChannelValueSwitch {
    private final Callback callback;

    public ChannelValueSwitch(final Callback callback) {
        this.callback = requireNonNull(callback);
    }

    public void doSwitch(ChannelValue channelValue) {
        if (channelValue instanceof DecimalValue) {
            callback.onDecimalValue((DecimalValue) channelValue);
        } else if (channelValue instanceof OnOff) {
            callback.onOnOff((OnOff) channelValue);
        } else if (channelValue instanceof OpenClose) {
            callback.onOpenClose((OpenClose) channelValue);
        } else if (channelValue instanceof PercentValue) {
            callback.onPercentValue((PercentValue) channelValue);
        } else if (channelValue instanceof RgbValue) {
            callback.onRgbValue((RgbValue) channelValue);
        } else if (channelValue instanceof StoppableOpenClose) {
            callback.onStoppableOpenClose((StoppableOpenClose) channelValue);
        } else if (channelValue instanceof TemperatureAndHumidityValue) {
            callback.onTemperatureAndHumidityValue((TemperatureAndHumidityValue) channelValue);
        } else if (channelValue instanceof UnknownValue) {
            callback.onUnknownValue((UnknownValue) channelValue);
        } else {
            throw new IllegalArgumentException(format("Don't know where to dispatch channel value with class %s! " +
                                                              "This should NEVER occur on production!",
                    channelValue.getClass().getSimpleName()));
        }
    }

    public interface Callback {
        void onDecimalValue(DecimalValue decimalValue);

        void onOnOff(OnOff onOff);

        void onOpenClose(OpenClose openClose);

        void onPercentValue(PercentValue percentValue);

        void onRgbValue(RgbValue rgbValue);

        void onStoppableOpenClose(StoppableOpenClose stoppableOpenClose);

        void onTemperatureAndHumidityValue(TemperatureAndHumidityValue temperatureAndHumidityValue);

        void onUnknownValue(UnknownValue unknownValue);
    }
}
