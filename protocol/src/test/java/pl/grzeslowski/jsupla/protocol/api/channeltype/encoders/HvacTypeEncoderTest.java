package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET;
import static pl.grzeslowski.jsupla.protocol.api.HvacMode.SUPLA_HVAC_MODE_COOL;

import java.math.BigDecimal;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

class HvacTypeEncoderTest {
    private final HvacTypeEncoder encoder = new HvacTypeEncoder();

    @Test
    void shouldEncodeFullPayload() {
        var flags =
                Set.of(
                        SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET,
                        SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET);
        var value =
                new HvacValue(
                        true,
                        SUPLA_HVAC_MODE_COOL,
                        BigDecimal.valueOf(20.5),
                        BigDecimal.valueOf(18.0),
                        flags);

        byte[] bytes = encoder.encode(value);
        HVACValue decoded = HVACValueDecoder.INSTANCE.decode(bytes);

        assertThat(decoded.isOn()).isEqualTo((short) 1);
        assertThat(decoded.mode()).isEqualTo((short) SUPLA_HVAC_MODE_COOL.getValue());
        assertThat(decoded.setpointTemperatureHeat()).isEqualTo((short) 2050);
        assertThat(decoded.setpointTemperatureCool()).isEqualTo((short) 1800);
        assertThat(decoded.flags()).isEqualTo((int) HvacFlag.toMask(flags));
    }
}
