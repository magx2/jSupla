package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEGATE;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ELECTRICITY_METER;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

class ChannelTypeDecoderTest {
    private static final ChannelTypeDecoder decoder = ChannelTypeDecoder.INSTANCE;

    @Test
    void shouldDecodeRelayType() {
        // given
        byte[] payload = new byte[] {1};

        // when
        ChannelValue value =
                decoder.decode(description(ChannelType.SUPLA_CHANNELTYPE_RELAY), payload);

        // then
        assertThat(value).isEqualTo(OnOffValue.ON);
    }

    @Test
    void shouldDecodeThermometerDoubleType() {
        // given
        byte[] payload =
                ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(-12.5).array();

        // when
        ChannelValue value =
                decoder.decode(description(ChannelType.SUPLA_CHANNELTYPE_THERMOMETER), payload);

        // then
        assertThat(value).isInstanceOf(TemperatureDoubleValue.class);
        TemperatureDoubleValue temperatureValue = (TemperatureDoubleValue) value;
        assertThat(temperatureValue.temperature()).isEqualTo(BigDecimal.valueOf(-12.5));
    }

    @Test
    void shouldDecodeElectricityMeterDoubleType() {
        // given
        byte[] payload = new byte[] {7, -60, -38, 2, 0, 0, 0, 0};

        // when
        ChannelValue value =
                decoder.decode(description(SUPLA_CHANNELTYPE_ELECTRICITY_METER), payload);

        // then
        assertThat(value).isInstanceOf(ElectricityMeterSimpleValue.class);
        ElectricityMeterSimpleValue electricityMeterValue = (ElectricityMeterSimpleValue) value;
        assertThat(electricityMeterValue.totalForwardActiveEnergy())
                .isEqualByComparingTo(new BigDecimal("1870.76"));
    }

    @Test
    void shouldDecodeUnknownTypeAsUnknownValue() {
        // when
        ChannelValue value =
                decoder.decode(new ChannelDescription(null, Set.of(), Set.of()), new byte[0]);

        // then
        assertThat(value).isInstanceOf(UnknownValue.class);
        assertThat(((UnknownValue) value).bytes()).isEmpty();
    }

    @Test
    void shouldFindTimerValueClassForTimerTypes() {
        assertThat(decoder.findClass(description(ChannelType.EV_TYPE_TIMER_STATE_V1)))
                .isEqualTo(TimerValue.class);
        assertThat(decoder.findClass(description(ChannelType.EV_TYPE_TIMER_STATE_V1_SEC)))
                .isEqualTo(TimerValue.class);
    }

    @Test
    void shouldFindElectricityMeterValueClassForAllElectricityMeterTypes() {
        assertThat(decoder.findClass(description(SUPLA_CHANNELTYPE_ELECTRICITY_METER)))
                .isEqualTo(ElectricityMeterSimpleValue.class);
        assertThat(
                        decoder.findClass(
                                description(ChannelType.EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1)))
                .isEqualTo(ElectricityMeterValue.class);
        assertThat(
                        decoder.findClass(
                                description(ChannelType.EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2)))
                .isEqualTo(ElectricityMeterValue.class);
        assertThat(
                        decoder.findClass(
                                description(ChannelType.EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V3)))
                .isEqualTo(ElectricityMeterValue.class);
    }

    @Test
    void shouldDecodeGateDescriptionAsGateValue() {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        Set.of(SUPLA_BIT_FUNC_CONTROLLINGTHEGATE));

        assertThat(decoder.decode(description, new byte[] {1})).isEqualTo(GateValue.OPEN);
        assertThat(decoder.findClass(description)).isEqualTo(GateValue.class);
    }

    private static ChannelDescription description(ChannelType type) {
        return new ChannelDescription(type, Set.of(), Set.of());
    }

    @ParameterizedTest(name = "{index}: should find only 0 or 1 decoder for channel type {0}")
    @MethodSource
    void oneDecoderPerChannelType(ChannelType type) {
        // when
        var decoders = decoder.streamOfDecoders(type).toList();

        // then
        assertThat(decoders.size())
                .as(
                        "Decoders for channel type %s: %s",
                        type,
                        decoders.stream()
                                .map(Object::getClass)
                                .map(Class::getSimpleName)
                                .collect(Collectors.joining(", ")))
                .isLessThanOrEqualTo(1);
    }

    static Stream<Arguments> oneDecoderPerChannelType() {
        return Arrays.stream(ChannelType.values()).map(Arguments::of);
    }
}
