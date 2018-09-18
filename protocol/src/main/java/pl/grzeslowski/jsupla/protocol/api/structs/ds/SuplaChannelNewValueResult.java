package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.CHAR_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelNewValueResult implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + CHAR_SIZE;
    /**
     * unsigned.
     */
    public final short channelNumber;
    public final int senderId;
    public final byte success;

    public SuplaChannelNewValueResult(short channelNumber, int senderId, byte success) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.senderId = senderId;
        this.success = success;
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT;
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
        if (!(o instanceof SuplaChannelNewValueResult)) {
            return false;
        }

        final SuplaChannelNewValueResult that = (SuplaChannelNewValueResult) o;

        if (channelNumber != that.channelNumber) {
            return false;
        }
        if (senderId != that.senderId) {
            return false;
        }
        return success == that.success;
    }

    @Override
    public int hashCode() {
        int result = (int) channelNumber;
        result = 31 * result + senderId;
        result = 31 * result + (int) success;
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelNewValueResult{" +
                "channelNumber=" + channelNumber +
                ", senderId=" + senderId +
                ", success=" + success +
                '}';
    }
}
