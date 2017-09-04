package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class ChannelNewValueResult implements DeviceServerEntity {
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int channelNumber;
    private final int senderId;
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int success;

    public ChannelNewValueResult(@PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int channelNumber,
                                 final int senderId,
                                 @Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int success) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.senderId = senderId;
        this.success = byteSize(success);
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getSuccess() {
        return success;
    }

    @Override
    public boolean equals(final Object o) {
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
    public int hashCode() {
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
