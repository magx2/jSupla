package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelE;

public class SuplaDeviceChannelEDecoderTest {
    private final SuplaDeviceChannelEDecoder decoder =
            new SuplaDeviceChannelEDecoder(HVACValueDecoder.INSTANCE);

    @Test
    public void shouldDecodeHvacChannelsUsingHvacDecoder() {
        short number = 9;
        int type = ChannelType.SUPLA_CHANNELTYPE_HVAC.getValue();
        int funcList = 123;
        int defaultValue = 77;
        long flags = 0x0203040506070809L;
        short offline = 2;
        long validity = 45;
        short defaultIcon = 12;
        short subDeviceId = 3;

        byte[] hvacBytes =
                ByteBuffer.allocate(HVACValue.SIZE)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) 1)
                        .put((byte) 2)
                        .putShort((short) 300)
                        .putShort((short) 400)
                        .putShort((short) 3)
                        .array();

        byte[] payload =
                baseHeader(number, type, funcList, defaultValue, flags, offline, validity)
                        .put(hvacBytes)
                        .put((byte) defaultIcon)
                        .put((byte) subDeviceId)
                        .array();

        SuplaDeviceChannelE result = decoder.decode(payload, 0);

        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.funcList()).isEqualTo(funcList);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.hvacValue())
                .isEqualTo(new HVACValue((short) 1, (short) 2, (short) 300, (short) 400, 3));
        assertThat(result.value()).isNull();
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
        assertThat(result.subDeviceId()).isEqualTo(subDeviceId);
    }

    @Test
    public void shouldDecodeThermometerOrRelayChannelsUsingRawValue() {
        short number = 2;
        int type = ChannelType.SUPLA_CHANNELTYPE_THERMOMETER.getValue();
        int funcList = 10;
        int defaultValue = 11;
        long flags = 100L;
        short offline = 1;
        long validity = 5;
        short defaultIcon = 7;
        short subDeviceId = 1;
        byte[] value = new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        value[3] = 3;

        byte[] payload =
                baseHeader(number, type, funcList, defaultValue, flags, offline, validity)
                        .put(value)
                        .put((byte) defaultIcon)
                        .put((byte) subDeviceId)
                        .array();

        SuplaDeviceChannelE result = decoder.decode(payload, 0);

        assertThat(result.value()).containsExactly(value);
        assertThat(result.hvacValue()).isNull();
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
        assertThat(result.subDeviceId()).isEqualTo(subDeviceId);
    }

    @Test
    public void shouldFailWhenTypeIsNotSupported() {
        byte[] payload =
                baseHeader((short) 1, 999, 0, 0, 0L, (short) 0, 0L)
                        .put(new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE])
                        .put((byte) 0)
                        .put((byte) 0)
                        .array();

        assertThatThrownBy(() -> decoder.decode(payload, 0))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("999");
    }

    private static ByteBuffer baseHeader(
            short number,
            int type,
            int funcList,
            int defaultValue,
            long flags,
            short offline,
            long validity) {
        return ByteBuffer.allocate(
                        1 + 4 + 4 + 4 + 8 + 1 + 4 + ProtoConsts.SUPLA_CHANNELVALUE_SIZE + 1 + 1)
                .order(ByteOrder.LITTLE_ENDIAN)
                .put((byte) number)
                .putInt(type)
                .putInt(funcList)
                .putInt(defaultValue)
                .putLong(flags)
                .put((byte) offline)
                .putInt((int) validity);
    }
}
