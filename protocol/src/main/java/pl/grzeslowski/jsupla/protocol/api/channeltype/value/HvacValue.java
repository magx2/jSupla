package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import java.util.Arrays;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
public class HvacValue implements ChannelValue {
    boolean on;
    Mode mode;
    Double setPointTemperatureHeat;
    Double setPointTemperatureCool;
    Flags flags;

    @Getter
    @RequiredArgsConstructor
    public static enum Mode {
        /**
         * Use SUPLA_HVAC_MODE_NOT_SET if you don't want to modify current mode, but only to alter temperature set points
         */
        NOT_SET(SUPLA_HVAC_MODE_NOT_SET),
        OFF(SUPLA_HVAC_MODE_OFF),
        HEAT(SUPLA_HVAC_MODE_HEAT),
        COOL(SUPLA_HVAC_MODE_COOL),
        HEAT_COOL(SUPLA_HVAC_MODE_HEAT_COOL),
        FAN_ONLY(SUPLA_HVAC_MODE_FAN_ONLY),
        DRY(SUPLA_HVAC_MODE_DRY);
        private final int mask;

        public boolean isOn(int flag) {
            return flag == mask;
        }

        @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
        public static Optional<Mode> findMode(int flag) {
            return Arrays.stream(values()).filter(mode -> mode.isOn(flag)).findAny();
        }
    }

    @RequiredArgsConstructor
    @Value
    @Builder
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
                    (flags & SUPLA_HVAC_VALUE_FLAG_BATTERY_COVER_OPEN) != 0);
        }

        public int toInt() {
            int result = 0;

            if (setPointTempHeatSet) {
                result |= SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET;
            }
            if (setPointTempCoolSet) {
                result |= SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET;
            }
            if (heating) {
                result |= SUPLA_HVAC_VALUE_FLAG_HEATING;
            }
            if (cooling) {
                result |= SUPLA_HVAC_VALUE_FLAG_COOLING;
            }
            if (weeklySchedule) {
                result |= SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE;
            }
            if (countdownTimer) {
                result |= SUPLA_HVAC_VALUE_FLAG_COUNTDOWN_TIMER;
            }
            if (fanEnabled) {
                result |= SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED;
            }
            if (thermometerError) {
                result |= SUPLA_HVAC_VALUE_FLAG_THERMOMETER_ERROR;
            }
            if (clockError) {
                result |= SUPLA_HVAC_VALUE_FLAG_CLOCK_ERROR;
            }
            if (forcedOffBySensor) {
                result |= SUPLA_HVAC_VALUE_FLAG_FORCED_OFF_BY_SENSOR;
            }
            if (cool) {
                result |= SUPLA_HVAC_VALUE_FLAG_COOL;
            }
            if (weeklyScheduleTemporalOverride) {
                result |= SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE_TEMPORAL_OVERRIDE;
            }
            if (batteryCoverOpen) {
                result |= SUPLA_HVAC_VALUE_FLAG_BATTERY_COVER_OPEN;
            }

            return result;
        }
    }
}
