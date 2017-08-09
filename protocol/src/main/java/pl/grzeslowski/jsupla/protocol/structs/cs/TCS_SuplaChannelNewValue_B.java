package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.call_types.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class TCS_SuplaChannelNewValue_B implements ClientServer {
    public final int channelId;
    public final byte[] value;

    public TCS_SuplaChannelNewValue_B(int channelId, byte[] value) {
        this.channelId = channelId;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }


    @Override
    public ClientServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return  INT_SIZE + SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "TCS_SuplaChannelNewValue_B{" +
                "channelId=" + channelId +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}