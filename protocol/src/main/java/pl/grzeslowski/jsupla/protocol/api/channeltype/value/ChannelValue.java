package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

public sealed interface ChannelValue
        permits ElectricityMeterValue,
                HeatpolThermostatValue,
                HumidityValue,
                HvacValue,
                OnOffValue,
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
