package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class ChannelClassSwitchTest {
    private final RecordingCallback callback = new RecordingCallback();
    private final ChannelClassSwitch<String> channelSwitch = new ChannelClassSwitch<>(callback);

    @Test
    public void shouldDispatchAllSupportedClasses() {
        assertDispatch(DecimalValue.class, "decimal");
        assertDispatch(OnOff.class, "onOff");
        assertDispatch(OpenClose.class, "openClose");
        assertDispatch(PercentValue.class, "percent");
        assertDispatch(RgbValue.class, "rgb");
        assertDispatch(StoppableOpenClose.class, "stoppable");
        assertDispatch(TemperatureAndHumidityValue.class, "temperatureAndHumidity");
        assertDispatch(TemperatureValue.class, "temperature");
        assertDispatch(ElectricityMeterValue.class, "electricity");
        assertDispatch(HvacValue.class, "hvac");
        assertDispatch(TimerValue.class, "timer");
        assertDispatch(UnknownValue.class, "unknown");
    }

    @Test
    public void shouldFailForUnsupportedChannelValueClass() {
        assertThatThrownBy(() -> channelSwitch.doSwitch(TestChannelValue.class))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(TestChannelValue.class.getSimpleName());
    }

    private void assertDispatch(Class<? extends ChannelValue> valueClass, String expected) {
        assertThat(channelSwitch.doSwitch(valueClass)).isEqualTo(expected);
        assertThat(callback.lastMethod).isEqualTo(expected);
    }

    private static final class RecordingCallback implements ChannelClassSwitch.Callback<String> {
        private String lastMethod;

        @Override
        public String onDecimalValue() {
            return record("decimal");
        }

        @Override
        public String onOnOff() {
            return record("onOff");
        }

        @Override
        public String onOpenClose() {
            return record("openClose");
        }

        @Override
        public String onPercentValue() {
            return record("percent");
        }

        @Override
        public String onRgbValue() {
            return record("rgb");
        }

        @Override
        public String onStoppableOpenClose() {
            return record("stoppable");
        }

        @Override
        public String onTemperatureValue() {
            return record("temperature");
        }

        @Override
        public String onTemperatureAndHumidityValue() {
            return record("temperatureAndHumidity");
        }

        @Override
        public String onElectricityMeter() {
            return record("electricity");
        }

        @Override
        public String onHvacValue() {
            return record("hvac");
        }

        @Override
        public String onTimerValue() {
            return record("timer");
        }

        @Override
        public String onUnknownValue() {
            return record("unknown");
        }

        private String record(String method) {
            lastMethod = method;
            return method;
        }
    }

    private static final class TestChannelValue implements ChannelValue {}
}
