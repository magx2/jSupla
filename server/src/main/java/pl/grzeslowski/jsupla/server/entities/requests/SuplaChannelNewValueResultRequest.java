package pl.grzeslowski.jsupla.server.entities.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;

public class SuplaChannelNewValueResultRequest {
    @Min(0)
    @Max(255)
    private final int channelNumber;
    private final int senderId;
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int success;

    public SuplaChannelNewValueResultRequest(int channelNumber, int senderId, int success) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuplaChannelNewValueResultRequest)) return false;

        SuplaChannelNewValueResultRequest that = (SuplaChannelNewValueResultRequest) o;

        if (channelNumber != that.channelNumber) return false;
        if (senderId != that.senderId) return false;
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
        return "SuplaChannelNewValueResultRequest{" +
                "channelNumber=" + channelNumber +
                ", senderId=" + senderId +
                ", success=" + success +
                '}';
    }
}
