package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import java.util.Arrays;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.ActionTriggerPropertiesDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.HVACValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelE;

public class SuplaDeviceChannelEDecoder
        implements pl.grzeslowski.jsupla.protocol.api.decoders.ds.DeviceServerDecoder<
                SuplaDeviceChannelE> {
    public static final SuplaDeviceChannelEDecoder INSTANCE =
            new SuplaDeviceChannelEDecoder(
                    HVACValueDecoder.INSTANCE, ActionTriggerPropertiesDecoder.INSTANCE);
    private final HVACValueDecoder hvacValueDecoder;
    private final ActionTriggerPropertiesDecoder actionTriggerPropertiesDecoder;
    private final int secondUnionSize =
            unionSize(
                    SUPLA_CHANNELVALUE_SIZE * BYTE_SIZE, // value
                    ActionTriggerProperties.SIZE, // actionTriggerProperties
                    HVACValue.SIZE); // hvacValue

    public SuplaDeviceChannelEDecoder(
            HVACValueDecoder hvacValueDecoder,
            ActionTriggerPropertiesDecoder actionTriggerPropertiesDecoder) {
        this.hvacValueDecoder = hvacValueDecoder;
        this.actionTriggerPropertiesDecoder = actionTriggerPropertiesDecoder;
    }

    private int unionSize(int... sizes) {
        return Arrays.stream(sizes).max().orElse(0);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public SuplaDeviceChannelE decode(byte[] bytes, int offset) {
        val number = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val type = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        if (type == SUPLA_CHANNELTYPE_ACTIONTRIGGER.getValue()) {
            val actionTriggerCaps = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
            offset += INT_SIZE;

            val defaultValue = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val flags = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
            offset += LONG_SIZE;

            val offline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val valueValidityTimeSec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            ActionTriggerProperties actionTriggerProperties =
                    actionTriggerPropertiesDecoder.decode(bytes, offset);
            offset += secondUnionSize;

            val defaultIcon = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val subDeviceId = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            return new SuplaDeviceChannelE(
                    number,
                    type,
                    null,
                    actionTriggerCaps,
                    null,
                    defaultValue,
                    flags,
                    offline,
                    valueValidityTimeSec,
                    null,
                    actionTriggerProperties,
                    null,
                    defaultIcon,
                    subDeviceId);
        }

        if (type == SUPLA_CHANNELTYPE_HVAC.getValue()) {
            val funcList = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val defaultValue = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val flags = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
            offset += LONG_SIZE;

            val offline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val valueValidityTimeSec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            HVACValue hvacValue = hvacValueDecoder.decode(bytes, offset);
            offset += secondUnionSize;

            val defaultIcon = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val subDeviceId = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            return new SuplaDeviceChannelE(
                    number,
                    type,
                    funcList,
                    null,
                    null,
                    defaultValue,
                    flags,
                    offline,
                    valueValidityTimeSec,
                    null,
                    null,
                    hvacValue,
                    defaultIcon,
                    subDeviceId);
        }

        if (type == SUPLA_CHANNELTYPE_THERMOMETER.getValue()
                || type == SUPLA_CHANNELTYPE_RELAY.getValue()) {
            val funcList = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val defaultValue = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val flags = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
            offset += LONG_SIZE;

            val offline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val valueValidityTimeSec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val value =
                    PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                            bytes, offset, offset + (int) SUPLA_CHANNELVALUE_SIZE);
            offset += secondUnionSize;

            val defaultIcon = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val subDeviceId = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            return new SuplaDeviceChannelE(
                    number,
                    type,
                    funcList,
                    null,
                    null,
                    defaultValue,
                    flags,
                    offline,
                    valueValidityTimeSec,
                    value,
                    null,
                    null,
                    defaultIcon,
                    subDeviceId);
        }

        if (type == SUPLA_CHANNELTYPE_DIMMER.getValue()) {
            val rgbwFuncList = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
            offset += INT_SIZE;

            val defaultValue = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val flags = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
            offset += LONG_SIZE;

            val offline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val valueValidityTimeSec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            offset += INT_SIZE;

            val value =
                    PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                            bytes, offset, offset + (int) SUPLA_CHANNELVALUE_SIZE);
            offset += secondUnionSize;

            val defaultIcon = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            val subDeviceId = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;

            return new SuplaDeviceChannelE(
                    number,
                    type,
                    null,
                    null,
                    rgbwFuncList,
                    defaultValue,
                    flags,
                    offline,
                    valueValidityTimeSec,
                    value,
                    null,
                    null,
                    defaultIcon,
                    subDeviceId);
        }

        throw new UnsupportedOperationException(
                format(
                        "Type %s is not supported for %s",
                        type, SuplaDeviceChannelE.class.getSimpleName()));
    }
}
