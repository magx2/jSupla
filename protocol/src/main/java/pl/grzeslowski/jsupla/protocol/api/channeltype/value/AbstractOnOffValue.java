package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

public sealed interface AbstractOnOffValue extends ChannelValue
        permits CurtainValue,
                DoorLockValue,
                FacadeBlindValue,
                GarageDoorValue,
                GateValue,
                GatewayLockValue,
                HeatOrColdSourceSwitchValue,
                LightSwitchValue,
                OnOffValue,
                PowerSwitchValue,
                ProjectorScreenValue,
                PumpSwitchValue,
                RollerGarageDoorValue,
                RollerShutterValue,
                RoofWindowValue,
                StaircaseTimerValue,
                TerraceAwningValue,
                VerticalBlindValue {
    OnOffValue toCommonBase();
}
