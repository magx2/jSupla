package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFlag.SUPLA_CHANNEL_FLAG_BATTERY_COVER_AVAILABLE;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFlag.SUPLA_CHANNEL_FLAG_BUTTON_MODE_SUPPORTED;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFlag.findByMask;

import org.junit.jupiter.api.Test;

class ChannelFlagTest {
    @Test
    void findByMaskShouldNotMatchBit31FlagWhenOnlyHigherBitIsSet() {
        var flags = findByMask(SUPLA_CHANNEL_FLAG_BUTTON_MODE_SUPPORTED.getValue());

        assertThat(flags)
                .containsExactly(SUPLA_CHANNEL_FLAG_BUTTON_MODE_SUPPORTED)
                .doesNotContain(SUPLA_CHANNEL_FLAG_BATTERY_COVER_AVAILABLE);
    }
}
