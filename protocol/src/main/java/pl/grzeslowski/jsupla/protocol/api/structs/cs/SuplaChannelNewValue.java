package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_CHANNEL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public final class SuplaChannelNewValue implements ClientServer {
    public static final int SIZE = BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE;
    public final byte channelId;
    public final byte[] value;

    public SuplaChannelNewValue(byte channelId, byte[] value) {
        this.channelId = channelId;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public ClientServerCallType callType() {
        return SUPLA_CS_CALL_CHANNEL_SET_VALUE;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaChannelNewValue)) {
            return false;
        }

        final SuplaChannelNewValue that = (SuplaChannelNewValue) o;

        if (channelId != that.channelId) {
            return false;
        }
        return Arrays.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        int result = (int) channelId;
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelNewValue{" +
            "channelId=" + channelId +
            ", value=" + Arrays.toString(value) +
            '}';
    }
}
