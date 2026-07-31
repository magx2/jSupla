package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AbstractOnOffValueTest {
    @ParameterizedTest(name = "{0} should map to {1}")
    @MethodSource("values")
    void shouldMapToCommonBase(AbstractOnOffValue value, OnOffValue expected) {
        assertThat(value.toCommonBase()).isEqualTo(expected);
    }

    static Stream<Arguments> values() {
        return Stream.of(
                Arguments.of(OnOffValue.ON, OnOffValue.ON),
                Arguments.of(OnOffValue.OFF, OnOffValue.OFF),
                Arguments.of(GatewayLockValue.UNLOCKED, OnOffValue.ON),
                Arguments.of(GatewayLockValue.LOCKED, OnOffValue.OFF),
                Arguments.of(GateValue.OPEN, OnOffValue.ON),
                Arguments.of(GateValue.CLOSE, OnOffValue.OFF),
                Arguments.of(GarageDoorValue.OPEN, OnOffValue.ON),
                Arguments.of(GarageDoorValue.CLOSE, OnOffValue.OFF),
                Arguments.of(DoorLockValue.UNLOCKED, OnOffValue.ON),
                Arguments.of(DoorLockValue.LOCKED, OnOffValue.OFF),
                Arguments.of(RollerShutterValue.OPEN, OnOffValue.ON),
                Arguments.of(RollerShutterValue.CLOSE, OnOffValue.OFF),
                Arguments.of(PowerSwitchValue.ON, OnOffValue.ON),
                Arguments.of(PowerSwitchValue.OFF, OnOffValue.OFF),
                Arguments.of(LightSwitchValue.ON, OnOffValue.ON),
                Arguments.of(LightSwitchValue.OFF, OnOffValue.OFF),
                Arguments.of(StaircaseTimerValue.ON, OnOffValue.ON),
                Arguments.of(StaircaseTimerValue.OFF, OnOffValue.OFF),
                Arguments.of(RoofWindowValue.OPEN, OnOffValue.ON),
                Arguments.of(RoofWindowValue.CLOSE, OnOffValue.OFF),
                Arguments.of(FacadeBlindValue.OPEN, OnOffValue.ON),
                Arguments.of(FacadeBlindValue.CLOSE, OnOffValue.OFF),
                Arguments.of(TerraceAwningValue.OPEN, OnOffValue.ON),
                Arguments.of(TerraceAwningValue.CLOSE, OnOffValue.OFF),
                Arguments.of(ProjectorScreenValue.OPEN, OnOffValue.ON),
                Arguments.of(ProjectorScreenValue.CLOSE, OnOffValue.OFF),
                Arguments.of(CurtainValue.OPEN, OnOffValue.ON),
                Arguments.of(CurtainValue.CLOSE, OnOffValue.OFF),
                Arguments.of(VerticalBlindValue.OPEN, OnOffValue.ON),
                Arguments.of(VerticalBlindValue.CLOSE, OnOffValue.OFF),
                Arguments.of(RollerGarageDoorValue.OPEN, OnOffValue.ON),
                Arguments.of(RollerGarageDoorValue.CLOSE, OnOffValue.OFF),
                Arguments.of(PumpSwitchValue.ON, OnOffValue.ON),
                Arguments.of(PumpSwitchValue.OFF, OnOffValue.OFF),
                Arguments.of(HeatOrColdSourceSwitchValue.ON, OnOffValue.ON),
                Arguments.of(HeatOrColdSourceSwitchValue.OFF, OnOffValue.OFF));
    }
}
