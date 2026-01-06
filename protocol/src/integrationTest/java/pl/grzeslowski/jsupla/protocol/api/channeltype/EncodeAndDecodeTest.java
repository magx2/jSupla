package pl.grzeslowski.jsupla.protocol.api.channeltype;

import static java.math.RoundingMode.HALF_UP;
import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_HVAC_VALUE_FLAG_ANTIFREEZE_OVERHEAT_ACTIVE;

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
                .map(Arguments::of);
    }

    @ParameterizedTest(name = "{index}: should encode and decode {0}")
    @MethodSource
    void encodeDecode(Class<? extends ChannelValue> channelValueClass) {
        // given
        var value = new ChannelClassSwitch<>(new ClassToObject()).doSwitch(channelValueClass);
        var channelType =
                Arrays.stream(values())
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

        private double randomTemperatureDouble() {
            return BigDecimal.valueOf(random.nextDouble())
                    .multiply(BigDecimal.valueOf(100))
                    .subtract(BigDecimal.valueOf(30))
                    .setScale(2, HALF_UP)
                    .doubleValue();
        }

        @Override
        public ChannelValue onHumidityValue() {
            return new HumidityValue(randomPercentage());
        }

        @Override
        public ChannelValue onTemperatureAndHumidityValue() {
            return new TemperatureAndHumidityValue(
                    BigDecimal.valueOf(randomTemperature()), (HumidityValue) onHumidityValue());
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
            var flags =
                    new HvacValue.Flags(
                            random.nextInt((int) SUPLA_HVAC_VALUE_FLAG_ANTIFREEZE_OVERHEAT_ACTIVE));
            return new HvacValue(
                    random.nextBoolean(),
                    HvacValue.Mode.values()[random.nextInt(HvacValue.Mode.values().length)],
                    flags.setPointTempHeatSet() ? randomTemperatureDouble() : null,
                    flags.setPointTempCoolSet() ? randomTemperatureDouble() : null,
                    flags);
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
        public ChannelValue onUnknownValue() {
            byte[] bytes = new byte[8];
            random.nextBytes(bytes);
            return new UnknownValue(bytes, "Message-" + random.nextInt(1_000));
        }
    }
}
