package pl.grzeslowski.jsupla.protocol.api.structs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@EqualsAndHashCode
@ToString
public final class ActionTriggerProperties implements ProtoWithSize {
    /**
     * ChannelNumber + 1
     * <p>
     * unsigned byte.
     */
    public final short relatedChannelNumber;
    /**
     * unsigned
     */
    public final long disablesLocalOperation;

    public ActionTriggerProperties(short relatedChannelNumber, long disablesLocalOperation) {
        this.relatedChannelNumber = unsignedByteSize(relatedChannelNumber);
        this.disablesLocalOperation = unsignedIntSize(disablesLocalOperation);
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE;
    }
}
