package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unionCheck;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsigned;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

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

    @SuppressWarnings("UnusedAssignment")
    @Override
    public SuplaDeviceChannelE decode(byte[] bytes, int offset) {
        val number = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val type = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        val firstUnion = parseFirstUnion(type, bytes, offset);
        offset += FirstUnion.SIZE;

        val defaultValue = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        val flags = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
        offset += LONG_SIZE;

        val offline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val valueValidityTimeSec = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        val secondUnion =
                parseSecondUnion(
                        type, bytes, offset, actionTriggerPropertiesDecoder, hvacValueDecoder);
        offset += SecondUnion.SIZE;

        val defaultIcon = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val subDeviceId = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        return new SuplaDeviceChannelE(
                number,
                type,
                firstUnion.funcList,
                firstUnion.actionTriggerCaps,
                firstUnion.rgbwFuncList,
                defaultValue,
                flags,
                offline,
                valueValidityTimeSec,
                secondUnion.value,
                secondUnion.actionTriggerProperties,
                secondUnion.hvacValue,
                defaultIcon,
                subDeviceId);
    }

    private FirstUnion parseFirstUnion(int type, byte[] bytes, int offset) {
        if (type == SUPLA_CHANNELTYPE_HVAC.getValue()
                || type == SUPLA_CHANNELTYPE_THERMOMETER.getValue()
                || type == SUPLA_CHANNELTYPE_RELAY.getValue()) {
            return FirstUnion.funcList(bytes, offset);
        }
        if (type == SUPLA_CHANNELTYPE_ACTIONTRIGGER.getValue()) {
            return FirstUnion.actionTriggerCaps(bytes, offset);
        }
        if (type == SUPLA_CHANNELTYPE_DIMMER.getValue()
                || type == SUPLA_CHANNELTYPE_RGBLEDCONTROLLER.getValue()
                || type == SUPLA_CHANNELTYPE_DIMMERANDRGBLED.getValue()) {
            return FirstUnion.rgbwFuncList(bytes, offset);
        }

        throw new UnsupportedOperationException(
                format(
                        "Type %s is not supported for %s when parsing first union",
                        type, SuplaDeviceChannelE.class.getSimpleName()));
    }

    private record FirstUnion(Integer funcList, Long actionTriggerCaps, Long rgbwFuncList) {
        @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
        static int SIZE = INT_SIZE;

        private FirstUnion {
            unsigned(funcList);
            unsigned(actionTriggerCaps);
            unsigned(rgbwFuncList);
            unionCheck(funcList, actionTriggerCaps, rgbwFuncList);
        }

        static FirstUnion funcList(byte[] bytes, int offset) {
            return new FirstUnion(PrimitiveDecoder.INSTANCE.parseInt(bytes, offset), null, null);
        }

        static FirstUnion actionTriggerCaps(byte[] bytes, int offset) {
            return new FirstUnion(
                    null, PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset), null);
        }

        static FirstUnion rgbwFuncList(byte[] bytes, int offset) {
            return new FirstUnion(
                    null, null, PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset));
        }
    }

    private SecondUnion parseSecondUnion(
            int type,
            byte[] bytes,
            int offset,
            ActionTriggerPropertiesDecoder actionTriggerPropertiesDecoder,
            HVACValueDecoder hvacValueDecoder) {
        if (type == SUPLA_CHANNELTYPE_DIMMER.getValue()
                || type == SUPLA_CHANNELTYPE_THERMOMETER.getValue()
                || type == SUPLA_CHANNELTYPE_RELAY.getValue()
                || type == SUPLA_CHANNELTYPE_RGBLEDCONTROLLER.getValue()
                || type == SUPLA_CHANNELTYPE_DIMMERANDRGBLED.getValue()) {
            return SecondUnion.value(bytes, offset);
        }
        if (type == SUPLA_CHANNELTYPE_ACTIONTRIGGER.getValue()) {
            return SecondUnion.actionTriggerProperties(
                    actionTriggerPropertiesDecoder, bytes, offset);
        }
        if (type == SUPLA_CHANNELTYPE_HVAC.getValue()) {
            return SecondUnion.hvacValue(hvacValueDecoder, bytes, offset);
        }

        throw new UnsupportedOperationException(
                format(
                        "Type %s is not supported for %s when parsing second union",
                        type, SuplaDeviceChannelE.class.getSimpleName()));
    }

    private record SecondUnion(
            byte[] value, ActionTriggerProperties actionTriggerProperties, HVACValue hvacValue) {
        /// Union size:
        /// * SUPLA_CHANNELVALUE_SIZE * BYTE_SIZE == 8
        /// * ActionTriggerProperties.SIZE == 5
        /// * HVACValue.SIZE == 8
        ///
        /// Therefore, the maximum is 8
        @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
        static int SIZE = 8;

        private SecondUnion {
            unionCheck(value, actionTriggerProperties, hvacValue);
        }

        static SecondUnion value(byte[] bytes, int offset) {
            return new SecondUnion(
                    PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                            bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE),
                    null,
                    null);
        }

        static SecondUnion actionTriggerProperties(
                ActionTriggerPropertiesDecoder decoder, byte[] bytes, int offset) {
            return new SecondUnion(null, decoder.decode(bytes, offset), null);
        }

        static SecondUnion hvacValue(HVACValueDecoder decoder, byte[] bytes, int offset) {
            return new SecondUnion(null, null, decoder.decode(bytes, offset));
        }
    }
}
