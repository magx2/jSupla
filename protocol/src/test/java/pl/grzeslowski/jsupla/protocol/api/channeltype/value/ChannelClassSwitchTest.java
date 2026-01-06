package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChannelClassSwitchTest {
    private final RecordingCallback callback = new RecordingCallback();
    private final ChannelClassSwitch<String> channelSwitch = new ChannelClassSwitch<>(callback);

    @Test
    void shouldDispatchAllSupportedClasses() {
        assertDispatch(OnOffValue.class, "onOff");
        assertDispatch(PercentValue.class, "percent");
        assertDispatch(RgbValue.class, "rgb");
        assertDispatch(TemperatureAndHumidityValue.class, "temperatureAndHumidity");
        assertDispatch(ElectricityMeterValue.class, "electricity");
        assertDispatch(HvacValue.class, "hvac");
        assertDispatch(TimerValue.class, "timer");
        assertDispatch(UnknownValue.class, "unknown");
    }

    private void assertDispatch(Class<? extends ChannelValue> valueClass, String expected) {
        assertThat(channelSwitch.doSwitch(valueClass)).isEqualTo(expected);
        assertThat(callback.lastMethod).isEqualTo(expected);
    }

    private static final class RecordingCallback implements ChannelClassSwitch.Callback<String> {
        private String lastMethod;

        @Override
        public String onOnOff() {
            return record("onOff");
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
        public String onTemperatureDoubleValue() {
            return "temperatureDouble";
        }

        @Override
        public String onHumidityValue() {
            return record("humidity");
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
}
