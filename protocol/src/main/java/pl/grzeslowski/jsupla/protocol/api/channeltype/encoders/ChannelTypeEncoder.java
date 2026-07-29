package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static lombok.AccessLevel.PRIVATE;

import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

@RequiredArgsConstructor(access = PRIVATE)
public class ChannelTypeEncoder {
    public static final ChannelTypeEncoder INSTANCE = new ChannelTypeEncoder();

    private final RgbTypeEncoder rgbTypeEncoder;
    private final HumidityTypeEncoder humidityTypeEncoder;
    private final HvacTypeEncoder hvacTypeEncoder;
    private final OnOffTypeEncoder onOffTypeEncoder;
    private final GateTypeEncoder gateTypeEncoder;
    private final SemanticRelayValueTypeEncoder semanticRelayValueTypeEncoder;
    private final PercentTypeEncoder percentTypeEncoder;
    private final ThermometerDoubleTypeEncoder thermometerDoubleTypeEncoder;
    private final ThermometerTypeEncoder thermometerTypeEncoder;
    private final PressureTypeEncoder pressureTypeEncoder;
    private final RainTypeEncoder rainTypeEncoder;
    private final WeightTypeEncoder weightTypeEncoder;
    private final WindTypeEncoder windTypeEncoder;
    private final HeatpolThermostatTypeEncoder heatpolThermostatTypeEncoder;

    private ChannelTypeEncoder() {
        this(
                new RgbTypeEncoder(),
                new HumidityTypeEncoder(),
                new HvacTypeEncoder(),
                new OnOffTypeEncoder(),
                new GateTypeEncoder(),
                new SemanticRelayValueTypeEncoder(),
                new PercentTypeEncoder(),
                new ThermometerDoubleTypeEncoder(),
                new ThermometerTypeEncoder(),
                new PressureTypeEncoder(),
                new RainTypeEncoder(),
                new WeightTypeEncoder(),
                new WindTypeEncoder(),
                new HeatpolThermostatTypeEncoder());
    }

    public byte[] encode(final ChannelValue channelValue) {
        return switch (channelValue) {
            case HumidityValue humidityValue -> humidityTypeEncoder.encode(humidityValue);
            case HvacValue hvacValue -> hvacTypeEncoder.encode(hvacValue);
            case OnOffValue onOffValue -> onOffTypeEncoder.encode(onOffValue);
            case GateValue gateValue -> gateTypeEncoder.encode(gateValue);
            case CurtainValue curtainValue -> semanticRelayValueTypeEncoder.encode(curtainValue);
            case DoorLockValue doorLockValue -> semanticRelayValueTypeEncoder.encode(doorLockValue);
            case FacadeBlindValue facadeBlindValue ->
                    semanticRelayValueTypeEncoder.encode(facadeBlindValue);
            case GarageDoorValue garageDoorValue ->
                    semanticRelayValueTypeEncoder.encode(garageDoorValue);
            case GatewayLockValue gatewayLockValue ->
                    semanticRelayValueTypeEncoder.encode(gatewayLockValue);
            case HeatOrColdSourceSwitchValue heatOrColdSourceSwitchValue ->
                    semanticRelayValueTypeEncoder.encode(heatOrColdSourceSwitchValue);
            case LightSwitchValue lightSwitchValue ->
                    semanticRelayValueTypeEncoder.encode(lightSwitchValue);
            case PowerSwitchValue powerSwitchValue ->
                    semanticRelayValueTypeEncoder.encode(powerSwitchValue);
            case ProjectorScreenValue projectorScreenValue ->
                    semanticRelayValueTypeEncoder.encode(projectorScreenValue);
            case PumpSwitchValue pumpSwitchValue ->
                    semanticRelayValueTypeEncoder.encode(pumpSwitchValue);
            case RollerGarageDoorValue rollerGarageDoorValue ->
                    semanticRelayValueTypeEncoder.encode(rollerGarageDoorValue);
            case RollerShutterValue rollerShutterValue ->
                    semanticRelayValueTypeEncoder.encode(rollerShutterValue);
            case RoofWindowValue roofWindowValue ->
                    semanticRelayValueTypeEncoder.encode(roofWindowValue);
            case StaircaseTimerValue staircaseTimerValue ->
                    semanticRelayValueTypeEncoder.encode(staircaseTimerValue);
            case TerraceAwningValue terraceAwningValue ->
                    semanticRelayValueTypeEncoder.encode(terraceAwningValue);
            case VerticalBlindValue verticalBlindValue ->
                    semanticRelayValueTypeEncoder.encode(verticalBlindValue);
            case PercentValue percentValue -> percentTypeEncoder.encode(percentValue);
            case RgbValue rgbValue -> rgbTypeEncoder.encode(rgbValue);
            case TemperatureAndHumidityValue temperatureAndHumidityValue ->
                    thermometerTypeEncoder.encode(temperatureAndHumidityValue);
            case TemperatureDoubleValue temperatureDoubleValue ->
                    thermometerDoubleTypeEncoder.encode(temperatureDoubleValue);
            case PressureValue pressureValue -> pressureTypeEncoder.encode(pressureValue);
            case RainValue rainValue -> rainTypeEncoder.encode(rainValue);
            case WeightValue weightValue -> weightTypeEncoder.encode(weightValue);
            case WindValue windValue -> windTypeEncoder.encode(windValue);
            case HeatpolThermostatValue heatpolThermostatValue ->
                    heatpolThermostatTypeEncoder.encode(heatpolThermostatValue);
            // unsupported types
            case ElectricityMeterSimpleValue electricityMeterSimpleValue ->
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.encode(ElectricityMeterSimpleValue)");
            case ElectricityMeterValue electricityMeterValue ->
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.encode(ElectricityMeterValue)");
            case TimerValue timerValue ->
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.encode(TimerValue)");
            case UnknownValue unknownValue ->
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.encode(UnknownValue)");
            case ActionTrigger actionTrigger ->
                    throw new UnsupportedOperationException(
                            "ChannelTypeEncoderImpl.encode(ActionTrigger)");
        };
    }
}
