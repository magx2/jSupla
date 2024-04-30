package pl.grzeslowski.jsupla.protocol.impl.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ThermostatExtendedValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ThermostatScheduleDecoderImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.ThermostatTimeDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.SHORT_SIZE;

@lombok.RequiredArgsConstructor
@javax.annotation.Generated(value = "Generated from TThermostat_ExtendedValue -> proto.h (supla core)", date = "2024-04-29T15:12:18.084+02:00[Europe/Belgrade]")
public final class ThermostatExtendedValueDecoderImpl implements Decoder<ThermostatExtendedValue> {
    public static final ThermostatExtendedValueDecoderImpl INSTANCE = new ThermostatExtendedValueDecoderImpl(PrimitiveDecoderImpl.INSTANCE, ThermostatTimeDecoderImpl.INSTANCE, ThermostatScheduleDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;
    private final ThermostatTimeDecoderImpl thermostatTimeDecoder;
    private final ThermostatScheduleDecoderImpl thermostatScheduleDecoder;

    @Override
    public ThermostatExtendedValue decode(byte[] bytes, int offset) {

        val fields = primitiveDecoder.parseUnsignedByte(bytes, offset);
        ;
        offset += BYTE_SIZE;

        val measuredTemperature = new short[10];
        for (int i = 0; i < 10; i++) {
            measuredTemperature[i] = primitiveDecoder.parseShort(bytes, offset);
            ;
        }
        offset += 10 * SHORT_SIZE;

        val presetTemperature = new short[10];
        for (int i = 0; i < 10; i++) {
            presetTemperature[i] = primitiveDecoder.parseShort(bytes, offset);
            ;
        }
        offset += 10 * SHORT_SIZE;

        val flags = new short[8];
        for (int i = 0; i < 8; i++) {
            flags[i] = primitiveDecoder.parseShort(bytes, offset);
            ;
        }
        offset += 8 * SHORT_SIZE;

        val values = new short[8];
        for (int i = 0; i < 8; i++) {
            values[i] = primitiveDecoder.parseShort(bytes, offset);
            ;
        }
        offset += 8 * SHORT_SIZE;

        val time = thermostatTimeDecoder.decode(bytes, offset);
        offset += time.size();

        val schedule = thermostatScheduleDecoder.decode(bytes, offset);
        ;
        offset += schedule.size();

        return new ThermostatExtendedValue(fields, measuredTemperature, presetTemperature, flags, values, time, schedule);
    }
}
