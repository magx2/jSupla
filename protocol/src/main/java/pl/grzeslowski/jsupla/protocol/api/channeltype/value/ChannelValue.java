package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

public sealed interface ChannelValue
        permits ActionTrigger,
                AbstractOnOffValue,
                ElectricityMeterSimpleValue,
                ElectricityMeterValue,
                HeatpolThermostatValue,
                HumidityValue,
                HvacValue,
                PercentValue,
                PressureValue,
                RainValue,
                RgbValue,
                TemperatureAndHumidityValue,
                TemperatureDoubleValue,
                TimerValue,
                UnknownValue,
                WeightValue,
                WindValue {}
