package pl.grzeslowski.jsupla.protocol.api.channeltype;

import static java.math.RoundingMode.HALF_UP;
import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.*;

import com.google.common.reflect.ClassPath;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;
import pl.grzeslowski.jsupla.protocol.api.HvacMode;
import pl.grzeslowski.jsupla.protocol.api.ThermostatValueFlag;
import pl.grzeslowski.jsupla.protocol.api.channeltype.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocol.api.channeltype.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

class EncodeAndDecodeTest {
    final Random random;
    final Logger log = org.slf4j.LoggerFactory.getLogger(EncodeAndDecodeTest.class);
    final ChannelTypeEncoder encoder = ChannelTypeEncoder.INSTANCE;
    final ChannelTypeDecoder decoder = ChannelTypeDecoder.INSTANCE;

    public EncodeAndDecodeTest() {
        var seed = Instant.now().toEpochMilli();
        log.info("Using random seed: {}", seed);
        random = new Random(seed);
    }

    @SuppressWarnings("UnstableApiUsage")
    static Stream<Arguments> encodeDecode() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getTopLevelClassesRecursive(ChannelValue.class.getPackage().getName())
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(ChannelValue.class::isAssignableFrom)
                // non encodable values
                .filter(not(UnknownValue.class::equals))
                .filter(not(ElectricityMeterValue.class::equals))
                .filter(not(TimerValue.class::equals))
                .filter(not(ActionTrigger.class::equals))
                .map(Arguments::of);
    }

    @ParameterizedTest(name = "{index}: should encode and decode {0}")
    @MethodSource
    void encodeDecode(Class<? extends ChannelValue> channelValueClass) {
        // given
        var value = new ChannelClassSwitch<>(new ClassToObject()).doSwitch(channelValueClass);
        var channelType =
                Arrays.stream(ChannelType.values())
                        .parallel()
                        .filter(type -> decoder.findClass(type) == channelValueClass)
                        .findAny()
                        .orElseThrow(
                                () ->
                                        new IllegalStateException(
                                                "No channel type found for channel value class: "
                                                        + channelValueClass.getSimpleName()));

        // when
        var encode = encoder.encode(value);
        var decode = decoder.decode(channelType, encode);

        // then
        assertThat(decode)
                .as(
                        "Decoded value should be equal to the original one. ChannelType=%s, "
                                + "value=%s, "
                                + "decode=%s",
                        channelType, value, decode)
                .isEqualTo(value);
    }

    class ClassToObject implements ChannelClassSwitch.Callback<ChannelValue> {

        @Override
        public ChannelValue onOnOff() {
            return random.nextBoolean() ? OnOffValue.ON : OnOffValue.OFF;
        }

        @Override
        public ChannelValue onPercentValue() {
            return new PercentValue(randomPercentage());
        }

        private int randomPercentage() {
            return random.nextInt(101);
        }

        @Override
        public ChannelValue onRgbValue() {
            return new RgbValue(
                    randomPercentage(),
                    randomPercentage(),
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255),
                    randomPercentage());
        }

        private int randomTemperature() {
            return random.nextInt(70) - 30;
        }

        @Override
        public ChannelValue onTemperatureDoubleValue() {
            return new TemperatureDoubleValue(randomTemperatureDouble());
        }

        private BigDecimal randomTemperatureDouble() {
            return BigDecimal.valueOf(random.nextDouble())
                    .multiply(BigDecimal.valueOf(100))
                    .subtract(BigDecimal.valueOf(30))
                    .setScale(2, HALF_UP);
        }

        @Override
        public ChannelValue onHumidityValue() {
            return new HumidityValue(randomPercentage());
        }

        @Override
        public ChannelValue onTemperatureAndHumidityValue() {
            return new TemperatureAndHumidityValue(
                    BigDecimal.valueOf(randomTemperature()),
                    Optional.of((HumidityValue) onHumidityValue()));
        }

        @Override
        public ChannelValue onElectricityMeter() {
            return new ElectricityMeterValue(
                    BigInteger.valueOf(random.nextInt(1_000_000)),
                    BigInteger.valueOf(random.nextInt(1_000_000)),
                    BigDecimal.valueOf(random.nextInt(1_000_000)),
                    BigDecimal.valueOf(random.nextInt(100_0000)),
                    Currency.getInstance("USD"),
                    random.nextInt(1_000_000),
                    random.nextInt(1_000_000),
                    List.of(randomPhase(), randomPhase(), randomPhase()));
        }

        private ElectricityMeterValue.Phase randomPhase() {
            return new ElectricityMeterValue.Phase(
                    BigInteger.valueOf(random.nextInt(1_000_000)),
                    BigInteger.valueOf(random.nextInt(1_000_000)),
                    BigInteger.valueOf(random.nextInt(1_000_000)),
                    BigInteger.valueOf(random.nextInt(1_000_000)),
                    random.nextDouble() * 25 + 230,
                    random.nextDouble(),
                    random.nextDouble() * 100,
                    random.nextDouble() * 100,
                    random.nextDouble() * 1000,
                    random.nextDouble() * 1000,
                    random.nextDouble() * 1000,
                    random.nextInt(50));
        }

        @Override
        public ChannelValue onHvacValue() {
            var flags = randomSet(HvacFlag.values());
            return new HvacValue(
                    random.nextBoolean(),
                    HvacMode.values()[random.nextInt(HvacMode.values().length)],
                    flags.contains(SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET)
                            ? randomTemperatureDouble()
                            : null,
                    flags.contains(SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET)
                            ? randomTemperatureDouble()
                            : null,
                    flags);
        }

        private <T> Set<T> randomSet(T[] values) {
            return randomSet(List.of(values));
        }

        private <T> Set<T> randomSet(Collection<T> values) {
            var set = new HashSet<T>();
            for (var value : values) {
                if (random.nextBoolean()) {
                    set.add(value);
                }
            }
            return Set.copyOf(set);
        }

        @Override
        public ChannelValue onTimerValue() {
            var targetValue = new byte[10];
            random.nextBytes(targetValue);
            return new TimerValue(
                    Duration.ofSeconds(random.nextLong(100_000)),
                    targetValue,
                    random.nextInt(100),
                    "SenderName-" + random.nextInt(1_000));
        }

        @Override
        public ChannelValue onPressureValue() {
            return new PressureValue(BigDecimal.valueOf(random.nextDouble() * 100));
        }

        @Override
        public ChannelValue onRainValue() {
            return new RainValue(BigDecimal.valueOf(random.nextDouble() * 100));
        }

        @Override
        public ChannelValue onWeightValue() {
            return new WeightValue(BigDecimal.valueOf(random.nextDouble() * 100));
        }

        @Override
        public ChannelValue onWindValue() {
            return new WindValue(BigDecimal.valueOf(random.nextDouble() * 100));
        }

        @Override
        public ChannelValue onHeatpolThermostatValue() {
            return new HeatpolThermostatValue(
                    random.nextBoolean(),
                    randomSet(ThermostatValueFlag.values()),
                    randomTemperatureDouble(),
                    randomTemperatureDouble());
        }

        @Override
        public ChannelValue onActionTrigger() {
            throw new UnsupportedOperationException("ClassToObject.onActionTrigger()");
        }

        @Override
        public ChannelValue onUnknownValue() {
            byte[] bytes = new byte[8];
            random.nextBytes(bytes);
            return new UnknownValue(bytes, "Message-" + random.nextInt(1_000));
        }
    }
}
