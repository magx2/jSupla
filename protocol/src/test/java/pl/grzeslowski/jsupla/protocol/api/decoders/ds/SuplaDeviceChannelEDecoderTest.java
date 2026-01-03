package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.decoders.ActionTriggerPropertiesDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelE;

class SuplaDeviceChannelEDecoderTest {
    private static final Set<ChannelType> SUPPORTED_CHANNEL_TYPES =
            Set.of(
                    SUPLA_CHANNELTYPE_HVAC,
                    SUPLA_CHANNELTYPE_THERMOMETER,
                    SUPLA_CHANNELTYPE_RELAY,
                    SUPLA_CHANNELTYPE_ACTIONTRIGGER,
                    SUPLA_CHANNELTYPE_DIMMER,
                    SUPLA_CHANNELTYPE_RGBLEDCONTROLLER,
                    SUPLA_CHANNELTYPE_DIMMERANDRGBLED);

    private final SuplaDeviceChannelEDecoder decoder =
            new SuplaDeviceChannelEDecoder(
                    HVACValueDecoder.INSTANCE, ActionTriggerPropertiesDecoder.INSTANCE);

    @ParameterizedTest(name = "{index}: should decode HVAC with {0} type")
    @MethodSource
    void shouldDecodeHvacChannelsUsingHvacDecoder(ChannelType channelType) {
        short number = 9;
        int type = channelType.getValue();
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

    static Stream<Arguments> shouldDecodeHvacChannelsUsingHvacDecoder() {
        return Stream.of(Arguments.of(SUPLA_CHANNELTYPE_HVAC));
    }

    @ParameterizedTest(name = "{index}: should decode raw value with {0} type")
    @MethodSource
    void shouldDecodeThermometerOrRelayChannelsUsingRawValue(ChannelType channelType) {
        short number = 2;
        int type = channelType.getValue();
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

    static Stream<Arguments> shouldDecodeThermometerOrRelayChannelsUsingRawValue() {
        return Stream.of(
                Arguments.of(SUPLA_CHANNELTYPE_THERMOMETER),
                Arguments.of(SUPLA_CHANNELTYPE_RELAY),
                Arguments.of(SUPLA_CHANNELTYPE_DIMMER),
                Arguments.of(SUPLA_CHANNELTYPE_RGBLEDCONTROLLER),
                Arguments.of(SUPLA_CHANNELTYPE_DIMMERANDRGBLED));
    }

    @ParameterizedTest(name = "{index}: should fail for unsupported channel type {0}")
    @MethodSource
    void shouldFailForUnsupportedChannelTypes(ChannelType unsupportedChannelType) {
        byte[] payload =
                baseHeader((short) 1, unsupportedChannelType.getValue(), 0, 0, 0L, (short) 0, 0L)
                        .put(new byte[(int) ProtoConsts.SUPLA_CHANNELVALUE_SIZE])
                        .put((byte) 0)
                        .put((byte) 0)
                        .array();

        assertThatThrownBy(() -> decoder.decode(payload, 0))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining(String.valueOf(unsupportedChannelType.getValue()));
    }

    static Stream<Arguments> shouldFailForUnsupportedChannelTypes() {
        return Arrays.stream(ChannelType.values())
                .filter(channelType -> !SUPPORTED_CHANNEL_TYPES.contains(channelType))
                .map(Arguments::of);
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

    @ParameterizedTest(name = "{index}: should decode action trigger with {0} type")
    @MethodSource
    void shouldDecodeActionTriggerChannel(ChannelType channelType) {
        // given
        short number = 3;
        int type = channelType.getValue();
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

    static Stream<Arguments> shouldDecodeActionTriggerChannel() {
        return Stream.of(Arguments.of(SUPLA_CHANNELTYPE_ACTIONTRIGGER));
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

    @ParameterizedTest(name = "{index}: should decode SuplaDeviceChannelE with {0} type")
    @MethodSource
    void decodeLed(ChannelType rgbwChannelType) {
        // given
        final byte number = 1;
        final int type = SUPLA_CHANNELTYPE_DIMMER.getValue();
        final long rgbwFuncList = 123L;
        final int defaultValue = 456;
        final long flags = 789L;
        final byte offline = 0;
        final int valueValidityTimeSec = 10;
        final byte[] value = new byte[ProtoConsts.SUPLA_CHANNELVALUE_SIZE];
        value[0] = 11;
        value[1] = 22;
        final byte defaultIcon = 5;
        final byte subDeviceId = 6;

        final byte[] bytes =
                ByteBuffer.allocate(36)
                        .order(LITTLE_ENDIAN)
                        .put(number)
                        .putInt(type)
                        .putInt((int) rgbwFuncList)
                        .putInt(defaultValue)
                        .putLong(flags)
                        .put(offline)
                        .putInt(valueValidityTimeSec)
                        .put(value)
                        .put(defaultIcon)
                        .put(subDeviceId)
                        .array();

        // when
        final SuplaDeviceChannelE result = decoder.decode(bytes);

        // then
        assertThat(result.number()).isEqualTo(number);
        assertThat(result.type()).isEqualTo(type);
        assertThat(result.rGBWFuncList()).isEqualTo(rgbwFuncList);
        assertThat(result.funcList()).isNull();
        assertThat(result.actionTriggerCaps()).isNull();
        assertThat(result.defaultValue()).isEqualTo(defaultValue);
        assertThat(result.flags()).isEqualTo(flags);
        assertThat(result.offline()).isEqualTo(offline);
        assertThat(result.valueValidityTimeSec()).isEqualTo(valueValidityTimeSec);
        assertThat(result.value()).isEqualTo(value);
        assertThat(result.actionTriggerProperties()).isNull();
        assertThat(result.hvacValue()).isNull();
        assertThat(result.defaultIcon()).isEqualTo(defaultIcon);
        assertThat(result.subDeviceId()).isEqualTo(subDeviceId);
    }

    static Stream<Arguments> decodeLed() {
        return Stream.of(
                Arguments.of(SUPLA_CHANNELTYPE_DIMMER),
                Arguments.of(SUPLA_CHANNELTYPE_RGBLEDCONTROLLER),
                Arguments.of(SUPLA_CHANNELTYPE_DIMMERANDRGBLED));
    }
}
