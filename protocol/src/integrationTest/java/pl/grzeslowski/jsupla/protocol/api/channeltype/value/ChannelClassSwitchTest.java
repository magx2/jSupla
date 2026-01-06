package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.google.common.reflect.ClassPath;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChannelClassSwitchTest {
    @SuppressWarnings("UnstableApiUsage")
    static Stream<Arguments> data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getTopLevelClassesRecursive(ChannelValue.class.getPackage().getName())
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(ChannelValue.class::isAssignableFrom)
                .map(Arguments::of);
    }

    @ParameterizedTest(name = "class = {0}")
    @MethodSource("data")
    void shouldDoClassSwitch(Class<? extends ChannelValue> clazz) {
        // given
        ChannelClassSwitch.Callback<String> callback = new Callback();
        ChannelClassSwitch<String> channelClassSwitch = new ChannelClassSwitch<>(callback);

        // when
        String result = channelClassSwitch.doSwitch(clazz);

        // then
        assertThat(result).isEqualTo(clazz.getSimpleName());
    }

    static class Callback implements ChannelClassSwitch.Callback<String> {
        @Override
        public String onDecimalValue() {
            return "DecimalValue";
        }

        @Override
        public String onOnOff() {
            return "OnOff";
        }

        @Override
        public String onOpenClose() {
            return "OpenClose";
        }

        @Override
        public String onPercentValue() {
            return "PercentValue";
        }

        @Override
        public String onRgbValue() {
            return "RgbValue";
        }

        @Override
        public String onStoppableOpenClose() {
            return "StoppableOpenClose";
        }

        @Override
        public String onTemperatureValue() {
            return "TemperatureValue";
        }

        @Override
        public String onHumidityValue() {
            return "HumidityValue";
        }

        @Override
        public String onTemperatureAndHumidityValue() {
            return "TemperatureAndHumidityValue";
        }

        @Override
        public String onElectricityMeter() {
            return "ElectricityMeterValue";
        }

        @Override
        public String onHvacValue() {
            return "HvacValue";
        }

        @Override
        public String onTimerValue() {
            return "TimerValue";
        }

        @Override
        public String onActionTrigger() {
            return "ActionTrigger";
        }

        @Override
        public String onUnknownValue() {
            return "UnknownValue";
        }
    }
}
