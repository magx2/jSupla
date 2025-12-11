package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

public sealed interface ChannelValue
        permits ActionTrigger,
                DecimalValue,
                ElectricityMeterValue,
                HvacValue,
                OnOff,
                OpenClose,
                PercentValue,
                RgbValue,
                StoppableOpenClose,
                TemperatureAndHumidityValue,
                TemperatureValue,
                TimerValue,
                UnknownValue {}
