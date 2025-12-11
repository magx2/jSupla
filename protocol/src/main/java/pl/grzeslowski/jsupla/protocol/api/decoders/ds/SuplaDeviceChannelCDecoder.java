package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ACTIONTRIGGER;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.ActionTriggerPropertiesDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelC;

@javax.annotation.Generated(
        value = "Struct original name: TDS_SuplaDeviceChannel_C",
        date = "2024-05-06T21:16:48.661+02:00[Europe/Belgrade]")
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaDeviceChannelCDecoder
        implements pl.grzeslowski.jsupla.protocol.api.decoders.ds.DeviceServerDecoder<
                SuplaDeviceChannelC> {
    public static final SuplaDeviceChannelCDecoder INSTANCE = new SuplaDeviceChannelCDecoder();

    @Override
    public SuplaDeviceChannelC decode(byte[] bytes, int offset) {
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

        int flags = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
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

        return new SuplaDeviceChannelC(
                number,
                type,
                funcList,
                actionTriggerCaps,
                defaultValue,
                flags,
                value,
                actionTriggerProperties,
                hvacValue);
    }
}
