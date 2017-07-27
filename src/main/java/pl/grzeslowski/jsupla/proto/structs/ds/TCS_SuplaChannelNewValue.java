package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.consts.CallTypes;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

@Deprecated
public final class TCS_SuplaChannelNewValue implements DeviceServer {
    public final byte  channelId;
    public final byte[] value;

    public TCS_SuplaChannelNewValue(byte channelId, byte[] value) {
        this.channelId = channelId;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public CallTypes callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE ;
    }

    @Override
    public String toString() {
        return "TCS_SuplaChannelNewValue{" +
                "channelId=" + channelId +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
