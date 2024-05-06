package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.Union;
import pl.grzeslowski.jsupla.protocol.api.traits.DeviceChannelTrait;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since v10.
 */
@EqualsAndHashCode
@ToString
public final class SuplaDeviceChannelC implements ProtoWithSize, DeviceChannelTrait {
    public static final int UNION_2_SIZE = JavaConsts.unionSize(BYTE_SIZE * SUPLA_CHANNELVALUE_SIZE, ActionTriggerProperties.SIZE, HVACValue.SIZE); // union 2 / value | actionTriggerProperties | hvacValue 
    public static final int SIZE =
        BYTE_SIZE + //number
            INT_SIZE + //type
            INT_SIZE + // union 1 / funcList | actionTriggerCaps
            INT_SIZE + // defaultValue 
            INT_SIZE + // flags 
            UNION_2_SIZE;

    /**
     * unsigned char
     */
    @Getter
    public final short number;
    @Getter
    public final int type;
    @Union(groupNumber = 1)
    public final Integer funcList;
    /**
     * unsigned
     */
    @Union(groupNumber = 1)
    public final Long actionTriggerCaps;
    /**
     * This field name should be default, but this is keyword in Java.
     */
    public final int defaultValue;
    public final int flags;

    @Union(groupNumber = 2)
    @Getter
    public final byte[] value;
    @Union(groupNumber = 2)
    public final ActionTriggerProperties actionTriggerProperties;
    @Union(groupNumber = 2)
    public final HVACValue hvacValue;

    public SuplaDeviceChannelC(short number, int type, Integer funcList, Long actionTriggerCaps, int defaultValue, int flags, byte[] value, ActionTriggerProperties actionTriggerProperties, HVACValue hvacValue) {
        this.number = unsignedByteSize(number);
        this.type = type;

        this.funcList = funcList;
        this.actionTriggerCaps = unsignedIntSize(actionTriggerCaps);
        unionCheck(funcList, actionTriggerCaps);

        this.defaultValue = defaultValue;
        this.flags = flags;

        this.value = value;
        if (value != null) {
            checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
        }
        this.actionTriggerProperties = actionTriggerProperties;
        this.hvacValue = hvacValue;
        unionCheck(value, actionTriggerProperties, hvacValue);
    }

    @Override
    public int size() {
        return SIZE;
    }
}
