package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.proto.consts.CallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.proto.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.proto.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class TCS_SuplaChannelNewValue_B implements DeviceServer {
    public final int channelId;
    public final byte[] value;

    public TCS_SuplaChannelNewValue_B(int channelId, byte[] value) {
        this.channelId = channelId;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }


    @Override
    public CallType callType() {
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
