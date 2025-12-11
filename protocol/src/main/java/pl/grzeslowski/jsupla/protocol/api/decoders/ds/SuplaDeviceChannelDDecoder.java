package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ACTIONTRIGGER;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.LONG_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.ActionTriggerPropertiesDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelD;

@javax.annotation.Generated(
        value = "Struct original name: TDS_SuplaDeviceChannel_D",
        date = "2024-05-12T14:09:10.232+02:00[Europe/Belgrade]")
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaDeviceChannelDDecoder implements DeviceServerDecoder<SuplaDeviceChannelD> {
    public static final SuplaDeviceChannelDDecoder INSTANCE = new SuplaDeviceChannelDDecoder();

    @Override
    public SuplaDeviceChannelD decode(byte[] bytes, int offset) {
        val number = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val type = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        val isActionTrigger = type == SUPLA_CHANNELTYPE_ACTIONTRIGGER.getValue();
        offset += INT_SIZE;

        // start union 1
        Integer funcList;
        Long actionTriggerCaps;
        if (!isActionTrigger) {
            funcList = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
            actionTriggerCaps = null;
        } else {
            funcList = null;
            actionTriggerCaps = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        }
        offset += INT_SIZE;
        // end union 1

        val defaultValue = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        val flags = PrimitiveDecoder.INSTANCE.parseLong(bytes, offset);
        offset += LONG_SIZE;

        val offline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        val valueValidityTimeSec = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        // start union 2
        byte[] value;
        ActionTriggerProperties actionTriggerProperties;
        if (!isActionTrigger) {
            value =
                    PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                            bytes, offset, offset + (int) SUPLA_CHANNELVALUE_SIZE);
            actionTriggerProperties = null;
        } else {
            value = null;
            actionTriggerProperties = ActionTriggerPropertiesDecoder.INSTANCE.decode(bytes, offset);
        }
        HVACValue hvacValue = null;
        offset += SUPLA_CHANNELVALUE_SIZE * BYTE_SIZE;
        // end union 2

        val defaultIcon = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        return new SuplaDeviceChannelD(
                number,
                type,
                funcList,
                actionTriggerCaps,
                defaultValue,
                flags,
                offline,
                valueValidityTimeSec,
                value,
                actionTriggerProperties,
                hvacValue,
                defaultIcon);
    }
}
