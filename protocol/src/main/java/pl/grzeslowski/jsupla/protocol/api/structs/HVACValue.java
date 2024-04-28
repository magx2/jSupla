package pl.grzeslowski.jsupla.protocol.api.structs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedShortSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.SHORT_SIZE;

@EqualsAndHashCode
@ToString
public final class HVACValue implements ProtoWithSize {
    /**
     * DS: 0/1 (or 0..100 ?)
     * <p>
     * unsigned byte.
     */
    public final short isOn;
    /**
     * SUPLA_HVAC_MODE_
     * <p>
     * unsigned byte.
     */
    public final short mode;
    /**
     * * 0.01 Celcius degree - used for heating
     */
    public final short setPointTemperatureHeat;
    /**
     * * 0.01 - Celcius degree used for cooling
     */
    public final short setPointTemperatureCool;
    /**
     * SUPLA_HVAC_VALUE_FLAG_
     * <p>
     * unsigned short
     */
    public final int flags;

    public HVACValue(short isOn, short mode, short setPointTemperatureHeat, short setPointTemperatureCool, int flags) {
        this.isOn = unsignedByteSize(isOn);
        this.mode = unsignedByteSize(mode);
        this.setPointTemperatureHeat = setPointTemperatureHeat;
        this.setPointTemperatureCool = setPointTemperatureCool;
        this.flags = unsignedShortSize(flags);
    }

    @Override
    public int size() {
        return BYTE_SIZE * 2 + SHORT_SIZE * 2;
    }
}
