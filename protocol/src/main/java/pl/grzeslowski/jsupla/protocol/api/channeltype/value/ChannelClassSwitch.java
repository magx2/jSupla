package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ChannelClassSwitch<T> {
    private final Callback<T> callback;

    public ChannelClassSwitch(final Callback<T> callback) {
        this.callback = requireNonNull(callback);
    }

    public T doSwitch(Class<? extends ChannelValue> channelClass) {
        if (channelClass.isAssignableFrom(OnOffValue.class)) {
            return callback.onOnOff();
        }
        if (channelClass.isAssignableFrom(GateValue.class)) {
            return callback.onGateValue();
        }
        if (channelClass.isAssignableFrom(GatewayLockValue.class)) {
            return callback.onGatewayLockValue();
        }
        if (channelClass.isAssignableFrom(GarageDoorValue.class)) {
            return callback.onGarageDoorValue();
        }
        if (channelClass.isAssignableFrom(DoorLockValue.class)) {
            return callback.onDoorLockValue();
        }
        if (channelClass.isAssignableFrom(RollerShutterValue.class)) {
            return callback.onRollerShutterValue();
        }
        if (channelClass.isAssignableFrom(PowerSwitchValue.class)) {
            return callback.onPowerSwitchValue();
        }
        if (channelClass.isAssignableFrom(LightSwitchValue.class)) {
            return callback.onLightSwitchValue();
        }
        if (channelClass.isAssignableFrom(StaircaseTimerValue.class)) {
            return callback.onStaircaseTimerValue();
        }
        if (channelClass.isAssignableFrom(RoofWindowValue.class)) {
            return callback.onRoofWindowValue();
        }
        if (channelClass.isAssignableFrom(FacadeBlindValue.class)) {
            return callback.onFacadeBlindValue();
        }
        if (channelClass.isAssignableFrom(TerraceAwningValue.class)) {
            return callback.onTerraceAwningValue();
        }
        if (channelClass.isAssignableFrom(ProjectorScreenValue.class)) {
            return callback.onProjectorScreenValue();
        }
        if (channelClass.isAssignableFrom(CurtainValue.class)) {
            return callback.onCurtainValue();
        }
        if (channelClass.isAssignableFrom(VerticalBlindValue.class)) {
            return callback.onVerticalBlindValue();
        }
        if (channelClass.isAssignableFrom(RollerGarageDoorValue.class)) {
            return callback.onRollerGarageDoorValue();
        }
        if (channelClass.isAssignableFrom(PumpSwitchValue.class)) {
            return callback.onPumpSwitchValue();
        }
        if (channelClass.isAssignableFrom(HeatOrColdSourceSwitchValue.class)) {
            return callback.onHeatOrColdSourceSwitchValue();
        }
        if (channelClass.isAssignableFrom(PercentValue.class)) {
            return callback.onPercentValue();
        }
        if (channelClass.isAssignableFrom(RgbValue.class)) {
            return callback.onRgbValue();
        }
        if (channelClass.isAssignableFrom(TemperatureAndHumidityValue.class)) {
            return callback.onTemperatureAndHumidityValue();
        }
        if (channelClass.isAssignableFrom(TemperatureDoubleValue.class)) {
            return callback.onTemperatureDoubleValue();
        }
        if (channelClass.isAssignableFrom(HumidityValue.class)) {
            return callback.onHumidityValue();
        }
        if (channelClass.isAssignableFrom(ElectricityMeterSimpleValue.class)) {
            return callback.onElectricityMeterSimple();
        }
        if (channelClass.isAssignableFrom(ElectricityMeterValue.class)) {
            return callback.onElectricityMeter();
        }
        if (channelClass.isAssignableFrom(HvacValue.class)) {
            return callback.onHvacValue();
        }
        if (channelClass.isAssignableFrom(TimerValue.class)) {
            return callback.onTimerValue();
        }
        if (channelClass.isAssignableFrom(PressureValue.class)) {
            return callback.onPressureValue();
        }
        if (channelClass.isAssignableFrom(RainValue.class)) {
            return callback.onRainValue();
        }
        if (channelClass.isAssignableFrom(WeightValue.class)) {
            return callback.onWeightValue();
        }
        if (channelClass.isAssignableFrom(WindValue.class)) {
            return callback.onWindValue();
        }
        if (channelClass.isAssignableFrom(HeatpolThermostatValue.class)) {
            return callback.onHeatpolThermostatValue();
        }
        if (channelClass.isAssignableFrom(ActionTrigger.class)) {
            return callback.onActionTrigger();
        }
        if (channelClass.isAssignableFrom(UnknownValue.class)) {
            return callback.onUnknownValue();
        }

        throw new IllegalArgumentException(
                format(
                        "Don't know where to dispatch channels value with class %s! "
                                + "This should NEVER occur on production!",
                        channelClass.getSimpleName()));
    }

    @SuppressWarnings("UnusedReturnValue")
    public interface Callback<T> {
        T onOnOff();

        T onGateValue();

        T onGatewayLockValue();

        T onGarageDoorValue();

        T onDoorLockValue();

        T onRollerShutterValue();

        T onPowerSwitchValue();

        T onLightSwitchValue();

        T onStaircaseTimerValue();

        T onRoofWindowValue();

        T onFacadeBlindValue();

        T onTerraceAwningValue();

        T onProjectorScreenValue();

        T onCurtainValue();

        T onVerticalBlindValue();

        T onRollerGarageDoorValue();

        T onPumpSwitchValue();

        T onHeatOrColdSourceSwitchValue();

        T onPercentValue();

        T onRgbValue();

        T onTemperatureDoubleValue();

        T onHumidityValue();

        T onTemperatureAndHumidityValue();

        T onElectricityMeterSimple();

        T onElectricityMeter();

        T onHvacValue();

        T onTimerValue();

        T onPressureValue();

        T onRainValue();

        T onWeightValue();

        T onWindValue();

        T onHeatpolThermostatValue();

        T onActionTrigger();

        T onUnknownValue();
    }
}
