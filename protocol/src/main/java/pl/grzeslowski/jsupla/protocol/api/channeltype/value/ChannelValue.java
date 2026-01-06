package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

public sealed interface ChannelValue
        permits ElectricityMeterValue,
                HumidityValue,
                HvacValue,
                OnOffValue,
                PercentValue,
                RgbValue,
                TemperatureAndHumidityValue,
                TemperatureDoubleValue,
                TemperatureValue,
                TimerValue,
                UnknownValue {}
