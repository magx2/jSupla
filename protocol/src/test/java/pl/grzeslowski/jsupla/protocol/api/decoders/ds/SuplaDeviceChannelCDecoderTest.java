package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelC;

public class SuplaDeviceChannelCDecoderTest {
    private final SuplaDeviceChannelCDecoder decoder = SuplaDeviceChannelCDecoder.INSTANCE;

    @Test
    public void shouldDecodeChannelWithValueUnion() {
        short number = 5;
        int type = 120;
        int funcList = 7;
        int defaultValue = 13;
        int flags = 99;
        byte[] value = new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        value[0] = 5;
        value[7] = 9;

        byte[] payload =
                ByteBuffer.allocate(1 + 4 + 4 + 4 + 4 + value.length)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) number)
                        .putInt(type)
                        .putInt(funcList)
                        .putInt(defaultValue)
                        .putInt(flags)
                        .put(value)
                        .array();

        SuplaDeviceChannelC result = decoder.decode(payload, 0);

        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.funcList()).isEqualTo(funcList);
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.value()).containsExactly(value);
        assertThat(result.actionTriggerProperties()).isNull();
        assertThat(result.hvacValue()).isNull();
    }
}
