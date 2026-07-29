package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

class SemanticRelayValueTypeEncoder implements ChannelValueEncoder<ChannelValue> {
    @Override
    public void encode(final ChannelValue value, final byte[] bytes) {
        bytes[0] = isActive(value) ? (byte) 1 : (byte) 0;
    }

    private boolean isActive(final ChannelValue value) {
        return switch (value) {
            case CurtainValue curtainValue -> curtainValue == CurtainValue.OPEN;
            case DoorLockValue doorLockValue -> doorLockValue == DoorLockValue.UNLOCKED;
            case FacadeBlindValue facadeBlindValue -> facadeBlindValue == FacadeBlindValue.OPEN;
            case GarageDoorValue garageDoorValue -> garageDoorValue == GarageDoorValue.OPEN;
            case GateValue gateValue -> gateValue == GateValue.OPEN;
            case GatewayLockValue gatewayLockValue -> gatewayLockValue == GatewayLockValue.UNLOCKED;
            case HeatOrColdSourceSwitchValue heatOrColdSourceSwitchValue ->
                    heatOrColdSourceSwitchValue == HeatOrColdSourceSwitchValue.ON;
            case LightSwitchValue lightSwitchValue -> lightSwitchValue == LightSwitchValue.ON;
            case PowerSwitchValue powerSwitchValue -> powerSwitchValue == PowerSwitchValue.ON;
            case ProjectorScreenValue projectorScreenValue ->
                    projectorScreenValue == ProjectorScreenValue.OPEN;
            case PumpSwitchValue pumpSwitchValue -> pumpSwitchValue == PumpSwitchValue.ON;
            case RollerGarageDoorValue rollerGarageDoorValue ->
                    rollerGarageDoorValue == RollerGarageDoorValue.OPEN;
            case RollerShutterValue rollerShutterValue ->
                    rollerShutterValue == RollerShutterValue.OPEN;
            case RoofWindowValue roofWindowValue -> roofWindowValue == RoofWindowValue.OPEN;
            case StaircaseTimerValue staircaseTimerValue ->
                    staircaseTimerValue == StaircaseTimerValue.ON;
            case TerraceAwningValue terraceAwningValue ->
                    terraceAwningValue == TerraceAwningValue.OPEN;
            case VerticalBlindValue verticalBlindValue ->
                    verticalBlindValue == VerticalBlindValue.OPEN;
            default ->
                    throw new UnsupportedOperationException(
                            "SemanticRelayValueTypeEncoder.encode("
                                    + value.getClass().getSimpleName()
                                    + ")");
        };
    }
}
