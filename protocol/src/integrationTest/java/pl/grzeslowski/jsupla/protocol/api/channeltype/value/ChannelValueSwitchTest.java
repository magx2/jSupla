package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.ActionTrigger.Capabilities.*;

import com.google.common.reflect.ClassPath;
import java.math.BigInteger;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

class ChannelValueSwitchTest {
    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    static Stream<Arguments> data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getTopLevelClassesRecursive(ChannelValue.class.getPackage().getName())
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(ChannelValue.class::isAssignableFrom)
                .map(clazz -> mock((Class<? extends ChannelValue>) clazz))
                .map(Arguments::of);
    }

    @ParameterizedTest(name = "class = {0}")
    @MethodSource("data")
    void shouldDoClassSwitch(ChannelValue channelValue) {
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
        public ChannelValue onTemperatureAndHumidityValue(
                TemperatureAndHumidityValue temperatureAndHumidityValue) {
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
        public ChannelValue onTimerValue(TimerValue channelValue) {
            return channelValue;
        }

        @Override
        public ChannelValue onActionTrigger(ActionTrigger channelValue) {
            return channelValue;
        }

        @Override
        public ChannelValue onUnknownValue(UnknownValue unknownValue) {
            return unknownValue;
        }
    }

    private static ChannelValue mock(Class<? extends ChannelValue> aClass) {
        ChannelClassSwitch.Callback<ChannelValue> callback =
                new ChannelClassSwitch.Callback<>() {
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
                                BigInteger.ONE, BigInteger.ONE, null, null, null, 1, 1, null);
                    }

                    @Override
                    public ChannelValue onHvacValue() {
                        return new HvacValue(
                                true, HvacValue.Mode.DRY, 1.1, 2.2, new HvacValue.Flags(0));
                    }

                    @Override
                    public ChannelValue onTimerValue() {
                        return new TimerValue(
                                Duration.ofSeconds(1),
                                new byte[ProtoConsts.SUPLA_CHANNELVALUE_SIZE],
                                1,
                                "test");
                    }

                    @Override
                    public ChannelValue onActionTrigger() {
                        return new ActionTrigger(Set.of(TOGGLE_x1, HOLD, TURN_ON));
                    }

                    @Override
                    public ChannelValue onUnknownValue() {
                        return UnknownValue.UNKNOWN_VALUE;
                    }
                };
        return new ChannelClassSwitch<>(callback).doSwitch(aClass);
    }
}
