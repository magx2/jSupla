package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

class ChannelTypeDecoderTest {
    private static final ChannelTypeDecoder decoder = ChannelTypeDecoder.INSTANCE;

    @Test
    void shouldDecodeRelayType() {
        // given
        byte[] payload = new byte[] {1};

        // when
        ChannelValue value = decoder.decode(ChannelType.SUPLA_CHANNELTYPE_RELAY, payload);

        // then
        assertThat(value).isEqualTo(OnOffValue.ON);
    }

    @Test
    void shouldDecodeThermometerDoubleType() {
        // given
        byte[] payload =
                ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(-12.5).array();

        // when
        ChannelValue value = decoder.decode(ChannelType.SUPLA_CHANNELTYPE_THERMOMETER, payload);

        // then
        assertThat(value).isInstanceOf(TemperatureDoubleValue.class);
        TemperatureDoubleValue temperatureValue = (TemperatureDoubleValue) value;
        assertThat(temperatureValue.temperature()).isEqualTo(BigDecimal.valueOf(-12.5));
    }

    @Test
    void shouldDecodeUnknownTypeAsUnknownValue() {
        // when
        ChannelValue value = decoder.decode(-1, new byte[0]);

        // then
        assertThat(value).isInstanceOf(UnknownValue.class);
        assertThat(((UnknownValue) value).bytes()).isEmpty();
    }

    @Test
    void shouldFindTimerValueClassForTimerTypes() {
        assertThat(decoder.findClass(ChannelType.EV_TYPE_TIMER_STATE_V1))
                .isEqualTo(TimerValue.class);
        assertThat(decoder.findClass(ChannelType.EV_TYPE_TIMER_STATE_V1_SEC))
                .isEqualTo(TimerValue.class);
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
