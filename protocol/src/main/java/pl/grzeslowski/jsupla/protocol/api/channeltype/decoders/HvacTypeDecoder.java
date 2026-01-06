package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_HVAC;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue.Mode.NOT_SET;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
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
        HvacValue.Flags flags = new HvacValue.Flags(decode.flags());
        return new HvacValue(
                decode.isOn() != 0,
                HvacValue.Mode.findMode(decode.mode())
                        .orElseGet(
                                () -> {
                                    log.warn("Cannot find mode for value={}", decode.mode());
                                    return NOT_SET;
                                }),
                findHeatSetPoint(decode, flags),
                findCoolSetPoint(decode, flags),
                flags);
    }

    private static Double findHeatSetPoint(HVACValue decode, HvacValue.Flags flags) {
        if (!flags.setPointTempHeatSet()) {
            return null;
        }
        return decode.setpointTemperatureHeat() * 0.01;
    }

    private static Double findCoolSetPoint(HVACValue decode, HvacValue.Flags flags) {
        if (!flags.setPointTempCoolSet()) {
            return null;
        }
        return decode.setpointTemperatureCool() * 0.01;
    }
}
