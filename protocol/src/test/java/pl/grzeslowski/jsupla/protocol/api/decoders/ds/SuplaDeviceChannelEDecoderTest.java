package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.decoders.ActionTriggerPropertiesDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelE;

class SuplaDeviceChannelEDecoderTest {
    private final SuplaDeviceChannelEDecoder decoder =
            new SuplaDeviceChannelEDecoder(
                    HVACValueDecoder.INSTANCE, ActionTriggerPropertiesDecoder.INSTANCE);

    @Test
    void shouldDecodeHvacChannelsUsingHvacDecoder() {
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
    void shouldDecodeThermometerOrRelayChannelsUsingRawValue() {
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
    void shouldFailWhenTypeIsNotSupported() {
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

    @Test
    void shouldDecodeActionTriggerChannel() {
        // given
        short number = 3;
        int type = 11000; // SUPLA_CHANNELTYPE_ACTIONTRIGGER
        long actionTriggerCaps = 55L;
        int defaultValue = 2;
        long flags = 0x0102030405060708L;
        short offline = 4;
        long valueValidity = 900L;
        short defaultIcon = 11;
        short subDeviceId = 13;

        // ActionTriggerProperties
        short relatedChannelNumber = 7;
        long disablesLocalOperation = 11;

        final byte[] actionTriggerPropertiesBytes =
                ByteBuffer.allocate(ActionTriggerProperties.SIZE)
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
                baseHeader(
                                number,
                                type,
                                (int) actionTriggerCaps,
                                defaultValue,
                                flags,
                                offline,
                                valueValidity)
                        .put(valueBytes)
                        .put((byte) defaultIcon)
                        .put((byte) subDeviceId)
                        .array();

        // when
        SuplaDeviceChannelE result = decoder.decode(payload, 0);

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
        assertThat(result.subDeviceId()).isEqualTo(subDeviceId);
        final ActionTriggerProperties actionTriggerProperties = result.actionTriggerProperties();
        assertThat(actionTriggerProperties).isNotNull();
        assertThat(actionTriggerProperties.relatedChannelNumber()).isEqualTo(relatedChannelNumber);
        assertThat(actionTriggerProperties.disablesLocalOperation())
                .isEqualTo(disablesLocalOperation);
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
