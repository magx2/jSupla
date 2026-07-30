package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.BitFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

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

    @ParameterizedTest(name = "{0} should decode as {3}")
    @MethodSource("semanticRelayValues")
    void shouldDecodeSemanticRelayFunctions(
            BitFunction function,
            ChannelValue activeValue,
            ChannelValue inactiveValue,
            Class<? extends ChannelValue> valueType) {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY, Set.of(), Set.of(function));

        assertThat(decoder.decode(new byte[] {1}, description)).isEqualTo(activeValue);
        assertThat(decoder.decode(new byte[] {0}, description)).isEqualTo(inactiveValue);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(valueType);
    }

    @ParameterizedTest(name = "{0} should reject unknown relay byte")
    @MethodSource("semanticRelayValues")
    void shouldFailForUnknownSemanticRelayValue(
            BitFunction function,
            ChannelValue activeValue,
            ChannelValue inactiveValue,
            Class<? extends ChannelValue> valueType) {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY, Set.of(), Set.of(function));

        assertThatThrownBy(() -> decoder.decode(new byte[] {3}, description))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        "Don't know how to map value 3 to " + valueType.getSimpleName());
    }

    @Test
    void shouldDecodeMultipleSemanticFunctionsAsOnOffValue() {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        Set.of(
                                BitFunction.SUPLA_BIT_FUNC_LIGHTSWITCH,
                                BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEGATE));

        ChannelValue value = decoder.decode(new byte[] {1}, description);

        assertThat(value).isEqualTo(OnOffValue.ON);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(OnOffValue.class);
    }

    @Test
    void shouldDecodeNonSemanticFunctionAsOnOffValue() {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        Set.of(BitFunction.SUPLA_BIT_FUNC_THERMOMETER));

        ChannelValue value = decoder.decode(new byte[] {1}, description);

        assertThat(value).isEqualTo(OnOffValue.ON);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(OnOffValue.class);
    }

    @Test
    void allBitFunctionsShouldBeCategorized() {
        var uncategorized = EnumSet.allOf(BitFunction.class);
        uncategorized.removeAll(RelayTypeDecoder.supportedSemanticFunctions());
        uncategorized.removeAll(
                Set.of(
                        BitFunction.SUPLA_BIT_FUNC_THERMOMETER,
                        BitFunction.SUPLA_BIT_FUNC_HUMIDITYANDTEMPERATURE,
                        BitFunction.SUPLA_BIT_FUNC_HUMIDITY,
                        BitFunction.SUPLA_BIT_FUNC_WINDSENSOR,
                        BitFunction.SUPLA_BIT_FUNC_PRESSURESENSOR,
                        BitFunction.SUPLA_BIT_FUNC_RAINSENSOR,
                        BitFunction.SUPLA_BIT_FUNC_WEIGHTSENSOR,
                        BitFunction.SUPLA_BIT_FUNC_HVAC_THERMOSTAT,
                        BitFunction.SUPLA_BIT_FUNC_HVAC_THERMOSTAT_HEAT_COOL,
                        BitFunction.SUPLA_BIT_FUNC_HVAC_THERMOSTAT_DIFFERENTIAL,
                        BitFunction.SUPLA_BIT_FUNC_HVAC_DOMESTIC_HOT_WATER));

        assertThat(uncategorized).isEmpty();
    }

    static Stream<Arguments> semanticRelayValues() {
        return Stream.of(
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEGATEWAYLOCK,
                        GatewayLockValue.UNLOCKED,
                        GatewayLockValue.LOCKED,
                        GatewayLockValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEGATE,
                        GateValue.OPEN,
                        GateValue.CLOSE,
                        GateValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEGARAGEDOOR,
                        GarageDoorValue.OPEN,
                        GarageDoorValue.CLOSE,
                        GarageDoorValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEDOORLOCK,
                        DoorLockValue.UNLOCKED,
                        DoorLockValue.LOCKED,
                        DoorLockValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEROLLERSHUTTER,
                        RollerShutterValue.OPEN,
                        RollerShutterValue.CLOSE,
                        RollerShutterValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_POWERSWITCH,
                        PowerSwitchValue.ON,
                        PowerSwitchValue.OFF,
                        PowerSwitchValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_LIGHTSWITCH,
                        LightSwitchValue.ON,
                        LightSwitchValue.OFF,
                        LightSwitchValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_STAIRCASETIMER,
                        StaircaseTimerValue.ON,
                        StaircaseTimerValue.OFF,
                        StaircaseTimerValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEROOFWINDOW,
                        RoofWindowValue.OPEN,
                        RoofWindowValue.CLOSE,
                        RoofWindowValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEFACADEBLIND,
                        FacadeBlindValue.OPEN,
                        FacadeBlindValue.CLOSE,
                        FacadeBlindValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_TERRACE_AWNING,
                        TerraceAwningValue.OPEN,
                        TerraceAwningValue.CLOSE,
                        TerraceAwningValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_PROJECTOR_SCREEN,
                        ProjectorScreenValue.OPEN,
                        ProjectorScreenValue.CLOSE,
                        ProjectorScreenValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_CURTAIN,
                        CurtainValue.OPEN,
                        CurtainValue.CLOSE,
                        CurtainValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_VERTICAL_BLIND,
                        VerticalBlindValue.OPEN,
                        VerticalBlindValue.CLOSE,
                        VerticalBlindValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_ROLLER_GARAGE_DOOR,
                        RollerGarageDoorValue.OPEN,
                        RollerGarageDoorValue.CLOSE,
                        RollerGarageDoorValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_PUMPSWITCH,
                        PumpSwitchValue.ON,
                        PumpSwitchValue.OFF,
                        PumpSwitchValue.class),
                Arguments.of(
                        BitFunction.SUPLA_BIT_FUNC_HEATORCOLDSOURCESWITCH,
                        HeatOrColdSourceSwitchValue.ON,
                        HeatOrColdSourceSwitchValue.OFF,
                        HeatOrColdSourceSwitchValue.class));
    }
}
