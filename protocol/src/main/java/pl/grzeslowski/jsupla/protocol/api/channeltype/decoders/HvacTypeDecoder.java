package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.math.RoundingMode.HALF_EVEN;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_HVAC;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET;
import static pl.grzeslowski.jsupla.protocol.api.HvacMode.SUPLA_HVAC_MODE_NOT_SET;

import java.math.BigDecimal;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;
import pl.grzeslowski.jsupla.protocol.api.HvacMode;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class HvacTypeDecoder implements ChannelValueDecoder<HvacValue> {
    public static final HvacTypeDecoder INSTANCE = new HvacTypeDecoder();
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

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

    private static BigDecimal findHeatSetPoint(HVACValue decode, Set<HvacFlag> flags) {
        return findSetPoint(
                decode.setpointTemperatureHeat(),
                SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET,
                flags);
    }

    private static BigDecimal findCoolSetPoint(HVACValue decode, Set<HvacFlag> flags) {
        return findSetPoint(
                decode.setpointTemperatureCool(),
                SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET,
                flags);
    }

    private static BigDecimal findSetPoint(short value, HvacFlag flag, Set<HvacFlag> flags) {
        return flags.contains(flag)
                ? BigDecimal.valueOf(value).setScale(2, HALF_EVEN).divide(ONE_HUNDRED, HALF_EVEN)
                : null;
    }
}
