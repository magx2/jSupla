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
        var callback = new Callback();
        var channelClassSwitch = new ChannelClassSwitch<>(callback);

        // when
        var result = channelClassSwitch.doSwitch(clazz);

        // then
        assertThat(result).isEqualTo(clazz);
    }

    static class Callback implements ChannelClassSwitch.Callback<Class<? extends ChannelValue>> {
        @Override
        public Class<? extends ChannelValue> onOnOff() {
            return OnOffValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onPercentValue() {
            return PercentValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onRgbValue() {
            return RgbValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onTemperatureValue() {
            return TemperatureValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onTemperatureDoubleValue() {
            return TemperatureDoubleValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onHumidityValue() {
            return HumidityValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onTemperatureAndHumidityValue() {
            return TemperatureAndHumidityValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onElectricityMeter() {
            return ElectricityMeterValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onHvacValue() {
            return HvacValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onTimerValue() {
            return TimerValue.class;
        }

        @Override
        public Class<? extends ChannelValue> onUnknownValue() {
            return UnknownValue.class;
        }
    }
}
