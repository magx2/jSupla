package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue.Mode.NOT_SET;

@Slf4j
public class HVACValueDecoderImpl implements Decoder<HvacValue> {
    public static final HVACValueDecoderImpl INSTANCE = new HVACValueDecoderImpl();

    private HVACValueDecoderImpl() {
    }

    @Override
    public HvacValue decode(byte[] bytes, int offset) {
        return decode(HVACValueDecoder.INSTANCE.decode(bytes, offset));
    }

    public HvacValue decode(HVACValue decode) {
        HvacValue.Flags flags = new HvacValue.Flags(decode.flags);
        return new HvacValue(
            decode.isOn != 0,
            HvacValue.Mode.findMode(decode.mode)
                .orElseGet(() -> {
                    log.warn("Cannot find mode for value={}", decode.mode);
                    return NOT_SET;
                }),
            findHeatDetPoint(decode, flags),
            findCoolSetPoint(decode, flags),
            flags);
    }

    private static Double findHeatDetPoint(HVACValue decode, HvacValue.Flags flags) {
        if (!flags.isSetPointTempHeatSet()) {
            return null;
        }
        return decode.setpointTemperatureHeat * 0.01;
    }

    private static Double findCoolSetPoint(HVACValue decode, HvacValue.Flags flags) {
        if (!flags.isSetPointTempCoolSet()) {
            return null;
        }
        return decode.setpointTemperatureCool * 0.01;
    }

}
