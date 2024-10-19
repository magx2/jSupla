package pl.grzeslowski.jsupla.protocol.api.channeltype.value;


import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Parameterized.class)
public class ChannelClassSwitchTest {
    final Class<? extends ChannelValue> clazz;

    @SuppressWarnings("UnstableApiUsage")
    @Parameterized.Parameters(name = "class = {0}")
    public static Object[][] data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
            .getTopLevelClassesRecursive(ChannelValue.class.getPackage().getName())
            .stream()
            .map(ClassPath.ClassInfo::load)
            .filter(clazz -> !clazz.isInterface())
            .filter(ChannelValue.class::isAssignableFrom)
            .map(clazz -> new Object[]{clazz})
            .toArray(Object[][]::new);
    }

    public ChannelClassSwitchTest(Class<? extends ChannelValue> clazz) {
        this.clazz = clazz;
    }

    @Test
    public void shouldDoClassSwitch() {
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
        public String onUnknownValue() {
            return "UnknownValue";
        }
    }
}