package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.grzeslowski.jsupla.protocol.api.BitFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.GateValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOffValue;

class RelayTypeDecoderTest {
    private final RelayTypeDecoder decoder = new RelayTypeDecoder();

    @Test
    void shouldDecodeOnValue() {
        assertThat(decoder.decode(new byte[] {1})).isEqualTo(OnOffValue.ON);
    }

    @Test
    void shouldDecodeOffValue() {
        assertThat(decoder.decode(new byte[] {0})).isEqualTo(OnOffValue.OFF);
    }

    @Test
    void shouldFailForUnknownValue() {
        assertThatThrownBy(() -> decoder.decode(new byte[] {3}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Don't know how to map value 3");
    }

    @ParameterizedTest
    @EnumSource(
            value = BitFunction.class,
            names = {
                "SUPLA_BIT_FUNC_CONTROLLINGTHEGATE",
                "SUPLA_BIT_FUNC_CONTROLLINGTHEGARAGEDOOR",
                "SUPLA_BIT_FUNC_ROLLER_GARAGE_DOOR"
            })
    void shouldDecodeGateFunctionsAsGateValue(BitFunction function) {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY, Set.of(), Set.of(function));

        assertThat(decoder.decode(new byte[] {1}, description)).isEqualTo(GateValue.OPEN);
        assertThat(decoder.decode(new byte[] {0}, description)).isEqualTo(GateValue.CLOSE);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(GateValue.class);
    }

    @Test
    void shouldDecodeNonGateFunctionAsOnOffValue() {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        Set.of(BitFunction.SUPLA_BIT_FUNC_POWERSWITCH));

        ChannelValue value = decoder.decode(new byte[] {1}, description);

        assertThat(value).isEqualTo(OnOffValue.ON);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(OnOffValue.class);
    }
}
