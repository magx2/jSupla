package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Parameterized.class)
public class ChannelValueSwitchTest {
    final ChannelValue channelValue;

    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    @Parameterized.Parameters(name = "class = {0}")
    public static ChannelValue[][] data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
            .getTopLevelClassesRecursive(ChannelValue.class.getPackage().getName())
            .stream()
            .map(ClassPath.ClassInfo::load)
            .filter(clazz -> !clazz.isInterface())
            .filter(ChannelValue.class::isAssignableFrom)
            .map(clazz -> mock((Class<? extends ChannelValue>) clazz))
            .map(channelValue -> new ChannelValue[]{channelValue})
            .toArray(ChannelValue[][]::new);
    }

    public ChannelValueSwitchTest(ChannelValue channelValue) {
        this.channelValue = channelValue;
    }

    @Test
    public void shouldDoClassSwitch() {
        // given
        Callback callback = new Callback();
        ChannelValueSwitch<ChannelValue> channelClassSwitch = new ChannelValueSwitch<>(callback);

        // when
        ChannelValue result = channelClassSwitch.doSwitch(channelValue);

        // then
        assertThat(result).isEqualTo(channelValue);
    }

    static class Callback implements ChannelValueSwitch.Callback<ChannelValue> {

        @Override
        public ChannelValue onDecimalValue(DecimalValue decimalValue) {
            return decimalValue;
        }

        @Override
        public ChannelValue onOnOff(OnOff onOff) {
            return onOff;
        }

        @Override
        public ChannelValue onOpenClose(OpenClose openClose) {
            return openClose;
        }

        @Override
        public ChannelValue onPercentValue(PercentValue percentValue) {
            return percentValue;
        }

        @Override
        public ChannelValue onRgbValue(RgbValue rgbValue) {
            return rgbValue;
        }

        @Override
        public ChannelValue onStoppableOpenClose(StoppableOpenClose stoppableOpenClose) {
            return stoppableOpenClose;
        }

        @Override
        public ChannelValue onTemperatureValue(TemperatureValue temperatureValue) {
            return temperatureValue;
        }

        @Override
        public ChannelValue onTemperatureAndHumidityValue(TemperatureAndHumidityValue temperatureAndHumidityValue) {
            return temperatureAndHumidityValue;
        }

        @Override
        public ChannelValue onElectricityMeter(ElectricityMeterValue electricityMeterValue) {
            return electricityMeterValue;
        }

        @Override
        public ChannelValue onHvacValue(HvacValue channelValue) {
            return channelValue;
        }

        @Override
        public ChannelValue onUnknownValue(UnknownValue unknownValue) {
            return unknownValue;
        }
    }

    private static ChannelValue mock(Class<? extends ChannelValue> aClass) {
        ChannelClassSwitch.Callback<ChannelValue> callback = new ChannelClassSwitch.Callback<ChannelValue>() {
            @Override
            public ChannelValue onDecimalValue() {
                return new DecimalValue(1);
            }

            @Override
            public ChannelValue onOnOff() {
                return OnOff.OFF;
            }

            @Override
            public ChannelValue onOpenClose() {
                return OpenClose.CLOSE;
            }

            @Override
            public ChannelValue onPercentValue() {
                return new PercentValue(1);
            }

            @Override
            public ChannelValue onRgbValue() {
                return new RgbValue(1, 2, 3, 4, 5);
            }

            @Override
            public ChannelValue onStoppableOpenClose() {
                return StoppableOpenClose.CLOSE;
            }

            @Override
            public ChannelValue onTemperatureValue() {
                return new TemperatureValue(1);
            }

            @Override
            public ChannelValue onTemperatureAndHumidityValue() {
                return new TemperatureAndHumidityValue(1, 2);
            }

            @Override
            public ChannelValue onElectricityMeter() {
                return new ElectricityMeterValue(
                    1,
                    1,
                    null,
                    null,
                    null,
                    1,
                    1,
                    null);
            }

            @Override
            public ChannelValue onHvacValue() {
                return new HvacValue(true, HvacValue.Mode.DRY, 1.1, 2.2, new HvacValue.Flags(0));
            }

            @Override
            public ChannelValue onUnknownValue() {
                return UnknownValue.UNKNOWN_VALUE;
            }
        };
        return new ChannelClassSwitch<>(callback).doSwitch(aClass);
    }
}
