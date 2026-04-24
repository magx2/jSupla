package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.Optional;
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
        assertThat(value.humidity()).contains(new HumidityValue(new BigDecimal("61.2")));
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
                        BigDecimal.ONE,
                        new BigDecimal("2"),
                        BigDecimal.TEN,
                        BigDecimal.ZERO,
                        new BigDecimal("230.0"),
                        new BigDecimal("5.5"),
                        new BigDecimal("100.0"),
                        new BigDecimal("3.5"),
                        new BigDecimal("110.0"),
                        new BigDecimal("0.9"),
                        new BigDecimal("45.0"),
                        new BigDecimal("50"));

        ElectricityMeterValue value =
                new ElectricityMeterValue(
                        new BigDecimal("111"),
                        new BigDecimal("222"),
                        new BigDecimal("333"),
                        new BigDecimal("444"),
                        new BigDecimal("123"),
                        new BigDecimal("321"),
                        BigDecimal.ONE,
                        new BigDecimal("12.34"),
                        Optional.of(Currency.getInstance("USD")),
                        5,
                        60,
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(phase),
                        Optional.empty(),
                        Optional.empty());

        assertThat(value.totalForwardActiveEnergyBalanced())
                .isEqualByComparingTo(new BigDecimal("123"));
        assertThat(value.totalForwardActiveEnergy()).isEqualByComparingTo(new BigDecimal("111"));
        assertThat(value.phase1()).contains(phase);
        assertThat(value.currency()).contains(Currency.getInstance("USD"));
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
