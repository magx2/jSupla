package pl.grzeslowski.jsupla.protocol.api.structs;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChannelConfigHVACTest {
    @Test
    public void size() {
        // given
        val proto = new ChannelConfigHVAC(
            1,
            null,
            null,
            (short) 2,
            3,
            null,
            (short) 5,
            (short) 6,
            7,
            8,
            9,
            10,
            (byte) 11,
            (short) 12,
            (short) 13,
            (short) 14,
            (short) 15,
            HvacParameterFlags.builder().build(),
            16,
            null,
            null,
            16,
            17,
            (short) 20,
            (short) 5,
            (short) 6,
            (short) 7,
            (short) 8,
            new HVACTemperatureCfg(1, new short[24]));

        // when
        int size = proto.size();

        // then
        assertThat(size).isEqualTo(126);
    }
}