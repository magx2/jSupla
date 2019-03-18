package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class ChannelNewValueResult implements DeviceServerEntity {
    @Min(0) // FIXME @random-bean problem
    @Max(UNSIGNED_BYTE_MAX)
    private final int channelNumber;
    private final int senderId;
    private final boolean success;

    public ChannelNewValueResult(@Min(0) @Max(UNSIGNED_BYTE_MAX) final int channelNumber,
                                 final int senderId,
                                 final boolean success) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.senderId = senderId;
        this.success = success;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public int getSenderId() {
        return senderId;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelNewValueResult)) {
            return false;
        }

        final ChannelNewValueResult that = (ChannelNewValueResult) o;

        if (channelNumber != that.channelNumber) {
            return false;
        }
        if (senderId != that.senderId) {
            return false;
        }
        return success == that.success;
    }

    @Override
    public final int hashCode() {
        int result = channelNumber;
        result = 31 * result + senderId;
        return result;
    }

    @Override
    public String toString() {
        return "ChannelNewValueResult{" +
                       "channelNumber=" + channelNumber +
                       ", senderId=" + senderId +
                       ", success=" + success +
                       '}';
    }
}
