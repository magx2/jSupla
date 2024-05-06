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
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.*;

@EqualsAndHashCode
@ToString
public final class SuplaDeviceChannelD implements ProtoWithSize, DeviceChannelTrait {
    public static final int UNION_2_SIZE = JavaConsts.unionSize(BYTE_SIZE, ActionTriggerProperties.SIZE, HVACValue.SIZE); // union 2 / value | actionTriggerProperties | hvacValue 
    public static final int SIZE =
        BYTE_SIZE + //number
            INT_SIZE + //type
            INT_SIZE + // union 1 / funcList | actionTriggerCaps
            INT_SIZE + // defaultValue 
            LONG_SIZE + // flags 
            BYTE_SIZE + //offline
            INT_SIZE + // valueValidityTimeSec
            UNION_2_SIZE +
            BYTE_SIZE // defaultIcon
        ;

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
    public final long flags;

    /**
     * unsigned
     */
    public final short offline;
    /**
     * unsigned
     */
    public final long valueValidityTimeSec;

    @Union(groupNumber = 2)
    @Getter
    public final byte[] value;
    @Union(groupNumber = 2)
    public final ActionTriggerProperties actionTriggerProperties;
    @Union(groupNumber = 2)
    public final HVACValue hvacValue;

    /**
     * unsigned
     */
    public final short defaultIcon;

    public SuplaDeviceChannelD(short number,
                               int type,
                               Integer funcList,
                               Long actionTriggerCaps,
                               int defaultValue,
                               long flags,
                               short offline,
                               long valueValidityTimeSec,
                               byte[] value,
                               ActionTriggerProperties actionTriggerProperties,
                               HVACValue hvacValue,
                               short defaultIcon) {
        this.number = unsignedByteSize(number);
        this.type = type;

        this.funcList = funcList;
        this.actionTriggerCaps = unsignedIntSize(actionTriggerCaps);
        unionCheck(funcList, actionTriggerCaps);

        this.defaultValue = defaultValue;
        this.flags = flags;
        this.offline = unsigned(offline);
        this.valueValidityTimeSec = unsigned(valueValidityTimeSec);

        this.value = value;
        this.actionTriggerProperties = actionTriggerProperties;
        this.hvacValue = hvacValue;
        unionCheck(value, actionTriggerProperties, hvacValue);

        this.defaultIcon = unsignedByteSize(defaultIcon);
    }

    @Override
    public int size() {
        return SIZE;
    }
}
