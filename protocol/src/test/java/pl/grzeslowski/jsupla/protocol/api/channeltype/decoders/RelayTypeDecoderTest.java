package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.ChannelFunction;
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
            ChannelFunction function,
            ChannelValue activeValue,
            ChannelValue inactiveValue,
            Class<? extends ChannelValue> valueType) {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        List.of(function),
                        Optional.of(function));

        assertThat(decoder.decode(new byte[] {1}, description)).isEqualTo(activeValue);
        assertThat(decoder.decode(new byte[] {0}, description)).isEqualTo(inactiveValue);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(valueType);
    }

    @ParameterizedTest(name = "{0} should reject unknown relay byte")
    @MethodSource("semanticRelayValues")
    void shouldFailForUnknownSemanticRelayValue(
            ChannelFunction function,
            ChannelValue activeValue,
            ChannelValue inactiveValue,
            Class<? extends ChannelValue> valueType) {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        List.of(function),
                        Optional.of(function));

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
                        List.of(
                                ChannelFunction.SUPLA_CHANNELFNC_LIGHTSWITCH,
                                ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEGATE),
                        Optional.empty());

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
                        List.of(ChannelFunction.SUPLA_CHANNELFNC_THERMOMETER),
                        Optional.empty());

        ChannelValue value = decoder.decode(new byte[] {1}, description);

        assertThat(value).isEqualTo(OnOffValue.ON);
        assertThat(decoder.getChannelValueType(description)).isEqualTo(OnOffValue.class);
    }

    @Test
    void semanticRelayValuesShouldBeSupportedFunctions() {
        var semanticRelayFunctions =
                semanticRelayValues()
                        .map(arguments -> (ChannelFunction) arguments.get()[0])
                        .collect(Collectors.toSet());

        assertThat(Set.copyOf(RelayTypeDecoder.supportedSemanticFunctions()))
                .isEqualTo(semanticRelayFunctions);
    }

    static Stream<Arguments> semanticRelayValues() {
        return Stream.of(
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEGATEWAYLOCK,
                        GatewayLockValue.UNLOCKED,
                        GatewayLockValue.LOCKED,
                        GatewayLockValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEGATE,
                        GateValue.OPEN,
                        GateValue.CLOSE,
                        GateValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEGARAGEDOOR,
                        GarageDoorValue.OPEN,
                        GarageDoorValue.CLOSE,
                        GarageDoorValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEDOORLOCK,
                        DoorLockValue.UNLOCKED,
                        DoorLockValue.LOCKED,
                        DoorLockValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEROLLERSHUTTER,
                        RollerShutterValue.OPEN,
                        RollerShutterValue.CLOSE,
                        RollerShutterValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_POWERSWITCH,
                        PowerSwitchValue.ON,
                        PowerSwitchValue.OFF,
                        PowerSwitchValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_LIGHTSWITCH,
                        LightSwitchValue.ON,
                        LightSwitchValue.OFF,
                        LightSwitchValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_STAIRCASETIMER,
                        StaircaseTimerValue.ON,
                        StaircaseTimerValue.OFF,
                        StaircaseTimerValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEROOFWINDOW,
                        RoofWindowValue.OPEN,
                        RoofWindowValue.CLOSE,
                        RoofWindowValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEFACADEBLIND,
                        FacadeBlindValue.OPEN,
                        FacadeBlindValue.CLOSE,
                        FacadeBlindValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_TERRACE_AWNING,
                        TerraceAwningValue.OPEN,
                        TerraceAwningValue.CLOSE,
                        TerraceAwningValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_PROJECTOR_SCREEN,
                        ProjectorScreenValue.OPEN,
                        ProjectorScreenValue.CLOSE,
                        ProjectorScreenValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_CURTAIN,
                        CurtainValue.OPEN,
                        CurtainValue.CLOSE,
                        CurtainValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_VERTICAL_BLIND,
                        VerticalBlindValue.OPEN,
                        VerticalBlindValue.CLOSE,
                        VerticalBlindValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_ROLLER_GARAGE_DOOR,
                        RollerGarageDoorValue.OPEN,
                        RollerGarageDoorValue.CLOSE,
                        RollerGarageDoorValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_PUMPSWITCH,
                        PumpSwitchValue.ON,
                        PumpSwitchValue.OFF,
                        PumpSwitchValue.class),
                Arguments.of(
                        ChannelFunction.SUPLA_CHANNELFNC_HEATORCOLDSOURCESWITCH,
                        HeatOrColdSourceSwitchValue.ON,
                        HeatOrColdSourceSwitchValue.OFF,
                        HeatOrColdSourceSwitchValue.class));
    }
}
