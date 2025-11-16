package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.Collections;
import java.util.Currency;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

public class ChannelValuesTest {
    @Test
    public void decimalValueShouldExposeBigDecimal() {
        assertThat(new DecimalValue("3.14").getValue()).isEqualTo(new BigDecimal("3.14"));
    }

    @Test
    public void percentValueShouldValidateRange() {
        assertThat(new PercentValue(25).getValue()).isEqualTo(25);
        assertThatThrownBy(() -> new PercentValue(101))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PercentValue(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void rgbValueShouldStoreUnsignedBytes() {
        RgbValue value = new RgbValue(255, 10, 20, 30, 40);

        assertThat(value.getBrightness()).isEqualTo(255);
        assertThat(value.getColorBrightness()).isEqualTo(10);
        assertThat(value.getRed()).isEqualTo(20);
        assertThat(value.getGreen()).isEqualTo(30);
        assertThat(value.getBlue()).isEqualTo(40);
    }

    @Test
    public void rgbValueShouldRejectValuesOutsideUnsignedByteRange() {
        assertThatThrownBy(() -> new RgbValue(256, 0, 0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new RgbValue(0, -1, 0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void temperatureValueShouldStoreBigDecimal() {
        assertThat(new TemperatureValue("20.5").getTemperature()).isEqualTo(new BigDecimal("20.5"));
    }

    @Test
    public void temperatureAndHumidityValueShouldStoreTwoBigDecimals() {
        TemperatureAndHumidityValue value = new TemperatureAndHumidityValue("18.5", "61.2");

        assertThat(value.getTemperature()).isEqualTo(new BigDecimal("18.5"));
        assertThat(value.getHumidity()).isEqualTo(new BigDecimal("61.2"));
    }

    @Test
    public void timerValueShouldExposeAllFields() {
        Duration remaining = Duration.ofSeconds(30);
        byte[] target = new byte[ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        TimerValue timerValue = new TimerValue(remaining, target, 42, "sender");

        assertThat(timerValue.getRemaining()).isEqualTo(remaining);
        assertThat(timerValue.getTargetValue()).isEqualTo(target);
        assertThat(timerValue.getSenderId()).isEqualTo(42);
        assertThat(timerValue.getSenderName()).isEqualTo("sender");
    }

    @Test
    public void unknownValueConstantShouldBeEmpty() {
        assertThat(UnknownValue.UNKNOWN_VALUE.getBytes()).isEmpty();
        assertThat(UnknownValue.UNKNOWN_VALUE.getMessage()).isEqualTo("UNKNOWN_VALUE");
    }

    @Test
    public void onOffOpenCloseAndStoppableEnumsShouldListAllStates() {
        assertThat(OnOff.values()).containsExactly(OnOff.ON, OnOff.OFF);
        assertThat(OpenClose.values()).containsExactly(OpenClose.OPEN, OpenClose.CLOSE);
        assertThat(StoppableOpenClose.values())
                .containsExactly(
                        StoppableOpenClose.OPEN, StoppableOpenClose.CLOSE, StoppableOpenClose.STOP);
    }

    @Test
    public void electricityMeterValueBuilderShouldCaptureFields() {
        ElectricityMeterValue.Phase phase =
                ElectricityMeterValue.Phase.builder()
                        .totalForwardActiveEnergy(BigInteger.ONE)
                        .totalReverseActiveEnergy(BigInteger.TWO)
                        .totalForwardReactiveEnergy(BigInteger.TEN)
                        .totalReverseReactiveEnergy(BigInteger.ZERO)
                        .voltage(230.0)
                        .current(5.5)
                        .powerActive(100.0)
                        .powerReactive(3.5)
                        .powerApparent(110.0)
                        .powerFactor(0.9)
                        .phaseAngle(45.0)
                        .frequency(50)
                        .build();

        ElectricityMeterValue value =
                ElectricityMeterValue.builder()
                        .totalForwardActiveEnergyBalanced(BigInteger.valueOf(123))
                        .totalReverseActiveEnergyBalanced(BigInteger.valueOf(321))
                        .totalCost(new BigDecimal("12.34"))
                        .pricePerUnit(BigDecimal.ONE)
                        .currency(Currency.getInstance("USD"))
                        .measuredValues(5)
                        .period(60)
                        .phases(Collections.singletonList(phase))
                        .build();

        assertThat(value.getTotalForwardActiveEnergyBalanced()).isEqualTo(BigInteger.valueOf(123));
        assertThat(value.getPhases()).containsExactly(phase);
        assertThat(value.getCurrency()).isEqualTo(Currency.getInstance("USD"));
    }

    @Test
    public void hvacFlagsShouldRoundTripBetweenBitmaskAndBooleans() {
        int mask =
                (int) ProtoConsts.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET
                        | (int) ProtoConsts.SUPLA_HVAC_VALUE_FLAG_COOLING
                        | (int) ProtoConsts.SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED;

        HvacValue.Flags flags = new HvacValue.Flags(mask);

        assertThat(flags.isSetPointTempHeatSet()).isTrue();
        assertThat(flags.isCooling()).isTrue();
        assertThat(flags.isFanEnabled()).isTrue();
        assertThat(flags.toInt()).isEqualTo(mask);
    }

    @Test
    public void hvacModeShouldBeFoundByMask() {
        assertThat(HvacValue.Mode.findMode(ProtoConsts.SUPLA_HVAC_MODE_COOL))
                .contains(HvacValue.Mode.COOL);
    }

    @Test
    public void channelValueShouldBeMarkerInterface() {
        assertThat(ChannelValue.class.isInterface()).isTrue();
        assertThat(ChannelValue.class.getDeclaredMethods()).isEmpty();
    }
}
