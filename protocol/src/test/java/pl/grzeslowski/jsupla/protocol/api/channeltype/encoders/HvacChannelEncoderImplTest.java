package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

public class HvacChannelEncoderImplTest {
    private final HvacChannelEncoderImpl encoder = new HvacChannelEncoderImpl();

    @Test
    public void shouldEncodeFullPayload() {
        HvacValue.Flags flags =
                HvacValue.Flags.builder()
                        .setPointTempHeatSet(true)
                        .setPointTempCoolSet(true)
                        .build();
        HvacValue value = new HvacValue(true, HvacValue.Mode.COOL, 20.5, 18.0, flags);

        byte[] bytes = encoder.encode(value);
        HVACValue decoded = HVACValueDecoder.INSTANCE.decode(bytes);

        assertThat(decoded.isOn).isEqualTo((short) 1);
        assertThat(decoded.mode).isEqualTo((short) HvacValue.Mode.COOL.getMask());
        assertThat(decoded.setpointTemperatureHeat).isEqualTo((short) 2050);
        assertThat(decoded.setpointTemperatureCool).isEqualTo((short) 1800);
        assertThat(decoded.flags).isEqualTo(flags.toInt());
    }
}
