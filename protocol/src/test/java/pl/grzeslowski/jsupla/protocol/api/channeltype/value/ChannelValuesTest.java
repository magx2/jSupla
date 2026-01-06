package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.Collections;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

class ChannelValuesTest {
    @Test
    void percentValueShouldValidateRange() {
        assertThat(new PercentValue(25).value()).isEqualTo(25);
        assertThatThrownBy(() -> new PercentValue(101))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PercentValue(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rgbValueShouldStoreUnsignedBytes() {
        RgbValue value = new RgbValue(100, 10, 20, 30, 40, 50);

        assertThat(value.brightness()).isEqualTo(100);
        assertThat(value.colorBrightness()).isEqualTo(10);
        assertThat(value.red()).isEqualTo(20);
        assertThat(value.green()).isEqualTo(30);
        assertThat(value.blue()).isEqualTo(40);
        assertThat(value.dimmerCct()).isEqualTo(50);
    }

    @Test
    void rgbValueShouldRejectValuesOutsideUnsignedByteRange() {
        assertThatThrownBy(() -> new RgbValue(256, 0, 0, 0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new RgbValue(0, -1, 0, 0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new RgbValue(0, 0, 0, 0, 0, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void temperatureAndHumidityValueShouldStoreTwoBigDecimals() {
        TemperatureAndHumidityValue value =
                new TemperatureAndHumidityValue(new BigDecimal("18.5"), new BigDecimal("61.2"));

        assertThat(value.temperature()).isEqualTo(new BigDecimal("18.5"));
        assertThat(value.humidity().humidity()).isEqualTo(new BigDecimal("61.2"));
    }

    @Test
    void timerValueShouldExposeAllFields() {
        Duration remaining = Duration.ofSeconds(30);
        byte[] target = new byte[ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        TimerValue timerValue = new TimerValue(remaining, target, 42, "sender");

        assertThat(timerValue.remaining()).isEqualTo(remaining);
        assertThat(timerValue.targetValue()).isEqualTo(target);
        assertThat(timerValue.senderId()).isEqualTo(42);
        assertThat(timerValue.senderName()).isEqualTo("sender");
    }

    @Test
    void unknownValueConstantShouldBeEmpty() {
        assertThat(UnknownValue.UNKNOWN_VALUE.bytes()).isEmpty();
        assertThat(UnknownValue.UNKNOWN_VALUE.message()).isEqualTo("UNKNOWN_VALUE");
    }

    @Test
    void electricityMeterValueBuilderShouldCaptureFields() {
        ElectricityMeterValue.Phase phase =
                new ElectricityMeterValue.Phase(
                        BigInteger.ONE,
                        BigInteger.TWO,
                        BigInteger.TEN,
                        BigInteger.ZERO,
                        230.0,
                        5.5,
                        100.0,
                        3.5,
                        110.0,
                        0.9,
                        45.0,
                        50);

        ElectricityMeterValue value =
                new ElectricityMeterValue(
                        BigInteger.valueOf(123),
                        BigInteger.valueOf(321),
                        new BigDecimal("12.34"),
                        BigDecimal.ONE,
                        Currency.getInstance("USD"),
                        5,
                        60,
                        Collections.singletonList(phase));

        assertThat(value.totalForwardActiveEnergyBalanced()).isEqualTo(BigInteger.valueOf(123));
        assertThat(value.phases()).containsExactly(phase);
        assertThat(value.currency()).isEqualTo(Currency.getInstance("USD"));
    }

    @Test
    void hvacFlagsShouldRoundTripBetweenBitmaskAndBooleans() {
        var mask =
                SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET.getValue()
                        | SUPLA_HVAC_VALUE_FLAG_COOLING.getValue()
                        | SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED.getValue();

        var flags = findByMask(mask);

        assertThat(flags)
                .contains(
                        SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET,
                        SUPLA_HVAC_VALUE_FLAG_COOLING,
                        SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED);
        assertThat(toMask(flags)).isEqualTo(mask);
    }

    @Test
    void channelValueShouldBeMarkerInterface() {
        assertThat(ChannelValue.class.isInterface()).isTrue();
        assertThat(ChannelValue.class.getDeclaredMethods()).isEmpty();
    }
}
