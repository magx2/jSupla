package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

class SemanticRelayValueTypeEncoderTest {
    private final ChannelTypeEncoder encoder = ChannelTypeEncoder.INSTANCE;

    @ParameterizedTest(name = "{0} should encode as {1}")
    @MethodSource("semanticRelayValues")
    void shouldEncodeSemanticRelayValues(ChannelValue value, byte expected) {
        assertThat(encoder.encode(value)[0]).isEqualTo(expected);
    }

    static Stream<Arguments> semanticRelayValues() {
        return Stream.of(
                Arguments.of(GatewayLockValue.UNLOCKED, (byte) 1),
                Arguments.of(GatewayLockValue.LOCKED, (byte) 0),
                Arguments.of(GateValue.OPEN, (byte) 1),
                Arguments.of(GateValue.CLOSE, (byte) 0),
                Arguments.of(GarageDoorValue.OPEN, (byte) 1),
                Arguments.of(GarageDoorValue.CLOSE, (byte) 0),
                Arguments.of(DoorLockValue.UNLOCKED, (byte) 1),
                Arguments.of(DoorLockValue.LOCKED, (byte) 0),
                Arguments.of(RollerShutterValue.OPEN, (byte) 1),
                Arguments.of(RollerShutterValue.CLOSE, (byte) 0),
                Arguments.of(PowerSwitchValue.ON, (byte) 1),
                Arguments.of(PowerSwitchValue.OFF, (byte) 0),
                Arguments.of(LightSwitchValue.ON, (byte) 1),
                Arguments.of(LightSwitchValue.OFF, (byte) 0),
                Arguments.of(StaircaseTimerValue.ON, (byte) 1),
                Arguments.of(StaircaseTimerValue.OFF, (byte) 0),
                Arguments.of(RoofWindowValue.OPEN, (byte) 1),
                Arguments.of(RoofWindowValue.CLOSE, (byte) 0),
                Arguments.of(FacadeBlindValue.OPEN, (byte) 1),
                Arguments.of(FacadeBlindValue.CLOSE, (byte) 0),
                Arguments.of(TerraceAwningValue.OPEN, (byte) 1),
                Arguments.of(TerraceAwningValue.CLOSE, (byte) 0),
                Arguments.of(ProjectorScreenValue.OPEN, (byte) 1),
                Arguments.of(ProjectorScreenValue.CLOSE, (byte) 0),
                Arguments.of(CurtainValue.OPEN, (byte) 1),
                Arguments.of(CurtainValue.CLOSE, (byte) 0),
                Arguments.of(VerticalBlindValue.OPEN, (byte) 1),
                Arguments.of(VerticalBlindValue.CLOSE, (byte) 0),
                Arguments.of(RollerGarageDoorValue.OPEN, (byte) 1),
                Arguments.of(RollerGarageDoorValue.CLOSE, (byte) 0),
                Arguments.of(PumpSwitchValue.ON, (byte) 1),
                Arguments.of(PumpSwitchValue.OFF, (byte) 0),
                Arguments.of(HeatOrColdSourceSwitchValue.ON, (byte) 1),
                Arguments.of(HeatOrColdSourceSwitchValue.OFF, (byte) 0));
    }
}
