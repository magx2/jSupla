package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelC;

class SuplaDeviceChannelCDecoderTest {
    private final SuplaDeviceChannelCDecoder decoder = SuplaDeviceChannelCDecoder.INSTANCE;

    @Test
    void shouldDecodeChannelWithValueUnion() {
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

    @Test
    void shouldDecodeChannelWithActionTriggerUnion() {
        // given
        short number = 5;
        int type = 11000; // SUPLA_CHANNELTYPE_ACTIONTRIGGER
        long actionTriggerCaps = 12345L;
        int defaultValue = 13;
        int flags = 99;

        // ActionTriggerProperties
        short relatedChannelNumber = 5;
        long disablesLocalOperation = 1;

        final byte[] actionTriggerPropertiesBytes =
                ByteBuffer.allocate(5)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) relatedChannelNumber)
                        .putInt((int) disablesLocalOperation)
                        .array();
        final byte[] valueBytes = new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        System.arraycopy(
                actionTriggerPropertiesBytes,
                0,
                valueBytes,
                0,
                actionTriggerPropertiesBytes.length);

        byte[] payload =
                ByteBuffer.allocate(1 + 4 + 4 + 4 + 4 + valueBytes.length)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) number)
                        .putInt(type)
                        .putInt((int) actionTriggerCaps)
                        .putInt(defaultValue)
                        .putInt(flags)
                        .put(valueBytes)
                        .array();

        // when
        SuplaDeviceChannelC result = decoder.decode(payload, 0);

        // then
        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.funcList()).isNull();
        assertThat(result.actionTriggerCaps()).isEqualTo(actionTriggerCaps);
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.value()).isNull();
        assertThat(result.hvacValue()).isNull();
        final ActionTriggerProperties actionTriggerProperties = result.actionTriggerProperties();
        assertThat(actionTriggerProperties).isNotNull();
        assertThat(actionTriggerProperties.relatedChannelNumber()).isEqualTo(relatedChannelNumber);
        assertThat(actionTriggerProperties.disablesLocalOperation())
                .isEqualTo(disablesLocalOperation);
    }
}
