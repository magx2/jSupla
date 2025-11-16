package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.Duration;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

public class ChannelValueSwitchTest {
    private final RecordingCallback callback = new RecordingCallback();
    private final ChannelValueSwitch<String> channelSwitch = new ChannelValueSwitch<>(callback);

    @Test
    public void shouldDispatchEachChannelValueImplementation() {
        assertDispatch(new DecimalValue(BigDecimal.ONE), "decimal");
        assertDispatch(OnOff.ON, "onOff");
        assertDispatch(OpenClose.OPEN, "openClose");
        assertDispatch(new PercentValue(40), "percent");
        assertDispatch(new RgbValue(1, 2, 3, 4, 5), "rgb");
        assertDispatch(StoppableOpenClose.STOP, "stoppable");
        assertDispatch(new TemperatureValue(21.5), "temperature");
        assertDispatch(new TemperatureAndHumidityValue(22.0, 55.5), "temperatureAndHumidity");
        assertDispatch(ElectricityMeterValue.builder().build(), "electricity");
        assertDispatch(
                new HvacValue(
                        true,
                        HvacValue.Mode.HEAT,
                        21.0,
                        25.0,
                        HvacValue.Flags.builder().heating(true).build()),
                "hvac");
        assertDispatch(
                new TimerValue(
                        Duration.ofSeconds(5),
                        new byte[ProtoConsts.SUPLA_CHANNELVALUE_SIZE],
                        10,
                        "sender"),
                "timer");
        assertDispatch(UnknownValue.UNKNOWN_VALUE, "unknown");
    }

    @Test
    public void shouldFailForUnsupportedInstance() {
        assertThatThrownBy(() -> channelSwitch.doSwitch(new ChannelValue() {}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void assertDispatch(ChannelValue channelValue, String expected) {
        assertThat(channelSwitch.doSwitch(channelValue)).isEqualTo(expected);
        assertThat(callback.lastMethod).isEqualTo(expected);
    }

    private static final class RecordingCallback implements ChannelValueSwitch.Callback<String> {
        private String lastMethod;

        @Override
        public String onDecimalValue(DecimalValue decimalValue) {
            return record("decimal");
        }

        @Override
        public String onOnOff(OnOff onOff) {
            return record("onOff");
        }

        @Override
        public String onOpenClose(OpenClose openClose) {
            return record("openClose");
        }

        @Override
        public String onPercentValue(PercentValue percentValue) {
            return record("percent");
        }

        @Override
        public String onRgbValue(RgbValue rgbValue) {
            return record("rgb");
        }

        @Override
        public String onStoppableOpenClose(StoppableOpenClose stoppableOpenClose) {
            return record("stoppable");
        }

        @Override
        public String onTemperatureValue(TemperatureValue temperatureValue) {
            return record("temperature");
        }

        @Override
        public String onTemperatureAndHumidityValue(
                TemperatureAndHumidityValue temperatureAndHumidityValue) {
            return record("temperatureAndHumidity");
        }

        @Override
        public String onElectricityMeter(ElectricityMeterValue electricityMeterValue) {
            return record("electricity");
        }

        @Override
        public String onHvacValue(HvacValue channelValue) {
            return record("hvac");
        }

        @Override
        public String onTimerValue(TimerValue channelValue) {
            return record("timer");
        }

        @Override
        public String onUnknownValue(UnknownValue unknownValue) {
            return record("unknown");
        }

        private String record(String method) {
            lastMethod = method;
            return method;
        }
    }
}
