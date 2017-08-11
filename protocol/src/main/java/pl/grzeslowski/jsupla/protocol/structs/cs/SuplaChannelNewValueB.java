package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType.SUPLA_CS_CALL_CHANNEL_SET_VALUE_B;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelNewValueB implements ClientServer {
    public final int channelId;
    public final byte[] value;

    public SuplaChannelNewValueB(int channelId, byte[] value) {
        this.channelId = channelId;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }


    @Override
    public ClientServerCallType callType() {
        return SUPLA_CS_CALL_CHANNEL_SET_VALUE_B;
    }

    @Override
    public int size() {
        return  INT_SIZE + SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "SuplaChannelNewValueB{" +
                "channelId=" + channelId +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
