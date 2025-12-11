package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.decoders.ActionTriggerPropertiesDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelD;

public class SuplaDeviceChannelDDecoderTest {
    private final SuplaDeviceChannelDDecoder decoder =
            new SuplaDeviceChannelDDecoder(
                    HVACValueDecoder.INSTANCE, ActionTriggerPropertiesDecoder.INSTANCE);

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
        assertThat(result.actionTriggerCaps()).isNull();
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.offline()).isEqualTo(offline);
        assertThat(result.valueValidityTimeSec()).isEqualTo(valueValidity);
        assertThat(result.value()).containsExactly(value);
        assertThat(result.actionTriggerProperties()).isNull();
        assertThat(result.hvacValue()).isNull();
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
    }

    @Test
    public void shouldDecodeDeviceChannelWithActionTriggerUnion() {
        // given
        short number = 3;
        int type = 11000; // SUPLA_CHANNELTYPE_ACTIONTRIGGER
        long actionTriggerCaps = 55L;
        int defaultValue = 2;
        long flags = 0x0102030405060708L;
        short offline = 4;
        long valueValidity = 900L;
        short defaultIcon = 11;

        // ActionTriggerProperties
        short relatedChannelNumber = 7;
        long disablesLocalOperation = 11;

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
                ByteBuffer.allocate(1 + 4 + 4 + 4 + 8 + 1 + 4 + valueBytes.length + 1)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) number)
                        .putInt(type)
                        .putInt((int) actionTriggerCaps)
                        .putInt(defaultValue)
                        .putLong(flags)
                        .put((byte) offline)
                        .putInt((int) valueValidity)
                        .put(valueBytes)
                        .put((byte) defaultIcon)
                        .array();

        // when
        SuplaDeviceChannelD result = decoder.decode(payload, 0);

        // then
        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.funcList()).isNull();
        assertThat(result.actionTriggerCaps()).isEqualTo(actionTriggerCaps);
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.offline()).isEqualTo(offline);
        assertThat(result.valueValidityTimeSec()).isEqualTo(valueValidity);
        assertThat(result.value()).isNull();
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
        assertThat(result.hvacValue()).isNull();
        final ActionTriggerProperties actionTriggerProperties = result.actionTriggerProperties();
        assertThat(actionTriggerProperties).isNotNull();
        assertThat(actionTriggerProperties.relatedChannelNumber()).isEqualTo(relatedChannelNumber);
        assertThat(actionTriggerProperties.disablesLocalOperation())
                .isEqualTo(disablesLocalOperation);
    }

    @Test
    public void shouldDecodeHvacChannel() {
        // given
        short number = 9;
        int type = ChannelType.SUPLA_CHANNELTYPE_HVAC.getValue();
        int funcList = 123;
        int defaultValue = 77;
        long flags = 0x0203040506070809L;
        short offline = 2;
        long valueValidity = 45L;
        short defaultIcon = 12;

        byte[] hvacBytes =
                ByteBuffer.allocate(HVACValue.SIZE)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) 1)
                        .put((byte) 2)
                        .putShort((short) 300)
                        .putShort((short) 400)
                        .putShort((short) 3)
                        .array();
        final byte[] valueBytes = new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        System.arraycopy(hvacBytes, 0, valueBytes, 0, hvacBytes.length);

        byte[] payload =
                ByteBuffer.allocate(1 + 4 + 4 + 4 + 8 + 1 + 4 + valueBytes.length + 1)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) number)
                        .putInt(type)
                        .putInt(funcList)
                        .putInt(defaultValue)
                        .putLong(flags)
                        .put((byte) offline)
                        .putInt((int) valueValidity)
                        .put(valueBytes)
                        .put((byte) defaultIcon)
                        .array();

        // when
        SuplaDeviceChannelD result = decoder.decode(payload, 0);

        // then
        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.funcList()).isEqualTo(funcList);
        assertThat(result.actionTriggerCaps()).isNull();
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.offline()).isEqualTo(offline);
        assertThat(result.valueValidityTimeSec()).isEqualTo(valueValidity);
        assertThat(result.value()).isNull();
        assertThat(result.actionTriggerProperties()).isNull();
        assertThat(result.hvacValue())
                .isEqualTo(new HVACValue((short) 1, (short) 2, (short) 300, (short) 400, 3));
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
    }
}
