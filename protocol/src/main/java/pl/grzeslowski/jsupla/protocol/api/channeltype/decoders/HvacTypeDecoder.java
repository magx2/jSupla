package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_HVAC;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET;
import static pl.grzeslowski.jsupla.protocol.api.HvacMode.SUPLA_HVAC_MODE_NOT_SET;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;
import pl.grzeslowski.jsupla.protocol.api.HvacMode;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

@Slf4j
class HvacTypeDecoder implements ChannelValueDecoder<HvacValue> {
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_HVAC);
    }

    @Override
    public Class<HvacValue> getChannelValueType() {
        return HvacValue.class;
    }

    @Override
    public HvacValue decode(byte[] bytes, int offset) {
        return decode(HVACValueDecoder.INSTANCE.decode(bytes, offset));
    }

    public HvacValue decode(HVACValue decode) {
        var flags = HvacFlag.findByMask(decode.flags());
        return new HvacValue(
                decode.isOn() != 0,
                HvacMode.findByValue(decode.mode())
                        .orElseGet(
                                () -> {
                                    log.warn("Cannot find mode for value={}", decode.mode());
                                    return SUPLA_HVAC_MODE_NOT_SET;
                                }),
                findHeatSetPoint(decode, flags),
                findCoolSetPoint(decode, flags),
                flags);
    }

    private static Double findHeatSetPoint(HVACValue decode, Set<HvacFlag> flags) {
        if (!flags.contains(SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET)) {
            return null;
        }
        return decode.setpointTemperatureHeat() * 0.01;
    }

    private static Double findCoolSetPoint(HVACValue decode, Set<HvacFlag> flags) {
        if (!flags.contains(SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET)) {
            return null;
        }
        return decode.setpointTemperatureCool() * 0.01;
    }
}
