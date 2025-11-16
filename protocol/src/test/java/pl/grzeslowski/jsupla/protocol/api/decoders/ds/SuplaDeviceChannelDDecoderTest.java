package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelD;

public class SuplaDeviceChannelDDecoderTest {
    private final SuplaDeviceChannelDDecoder decoder = SuplaDeviceChannelDDecoder.INSTANCE;

    @Test
    public void shouldDecodeDeviceChannelWithValueUnion() {
        short number = 3;
        int type = 77;
        int funcList = 1;
        int defaultValue = 2;
        long flags = 0x0102030405060708L;
        short offline = 4;
        long valueValidity = 900L;
        byte[] value = new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        value[1] = 1;
        value[6] = 6;
        short defaultIcon = 11;

        byte[] payload =
                ByteBuffer.allocate(1 + 4 + 4 + 4 + 8 + 1 + 4 + value.length + 1)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) number)
                        .putInt(type)
                        .putInt(funcList)
                        .putInt(defaultValue)
                        .putLong(flags)
                        .put((byte) offline)
                        .putInt((int) valueValidity)
                        .put(value)
                        .put((byte) defaultIcon)
                        .array();

        SuplaDeviceChannelD result = decoder.decode(payload, 0);

        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.funcList()).isEqualTo(funcList);
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.offline()).isEqualTo(offline);
        assertThat(result.valueValidityTimeSec()).isEqualTo(valueValidity);
        assertThat(result.value()).containsExactly(value);
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
    }
}
