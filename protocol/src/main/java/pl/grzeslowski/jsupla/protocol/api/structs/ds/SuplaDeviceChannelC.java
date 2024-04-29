package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.Union;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since v10.
 */
@EqualsAndHashCode
@ToString
public final class SuplaDeviceChannelC implements ProtoWithSize {
    public static final int SIZE =
        BYTE_SIZE + //number
            INT_SIZE + //type
            INT_SIZE + // union 1 / funcList | actionTriggerCaps
            INT_SIZE + // defaultValue 
            INT_SIZE + // flags 
            JavaConsts.union(BYTE_SIZE, ActionTriggerProperties.SIZE, HVACValue.SIZE) // union 2 / value | actionTriggerProperties | hvacValue
        ;

    /**
     * unsigned char
     */
    public final short number;
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
        union(funcList, actionTriggerCaps);

        this.defaultValue = defaultValue;
        this.flags = flags;

        this.value = value;
        this.actionTriggerProperties = actionTriggerProperties;
        this.hvacValue = hvacValue;
        union(value, actionTriggerProperties, hvacValue);
    }

    @Override
    public int size() {
        return SIZE;
    }
}
