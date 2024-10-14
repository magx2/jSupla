package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@Value
public class HvacValue implements ChannelValue {
    boolean on;
    Mode mode;
    Double setPointTemperatureHeat;
    Double setPointTemperatureCool;
    Flags flags;

    public static enum Mode {
        NOT_SET, HEAT, HEAT_COOL, DRY
    }

    @RequiredArgsConstructor
    @Value
    public static class Flags {
        boolean setPointTempHeatSet;
        boolean setPointTempCoolSet;
        boolean heating;
        boolean cooling;
        boolean weeklySchedule;
        boolean countdownTimer;
        boolean fanEnabled;
        boolean thermometerError;
        boolean clockError;
        boolean forcedOffBySensor;
        boolean cool;
        boolean weeklyScheduleTemporalOverride;
        boolean batteryCoverOpen;

        public Flags(int flags) {
            this(
                (flags & SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_HEATING) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_COOLING) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_COUNTDOWN_TIMER) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_THERMOMETER_ERROR) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_CLOCK_ERROR) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_FORCED_OFF_BY_SENSOR) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_COOL) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE_TEMPORAL_OVERRIDE) != 0,
                (flags & SUPLA_HVAC_VALUE_FLAG_BATTERY_COVER_OPEN) != 0
            );
        }
    }
}
