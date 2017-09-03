package pl.grzeslowski.jsupla.server.api.entities.requests.cs;

import pl.grzeslowski.jsupla.server.api.entities.requests.Request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;

public class ChannelNewValueRequest implements Request {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int channelId;

    public ChannelNewValueRequest(final int channelId) {
        this.channelId = byteSize(channelId);
    }

    public int getChannelId() {
        return channelId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelNewValueRequest)) {
            return false;
        }

        final ChannelNewValueRequest that = (ChannelNewValueRequest) o;

        return getChannelId() == that.getChannelId();
    }

    @Override
    public int hashCode() {
        return getChannelId();
    }

    @Override
    public String toString() {
        return "ChannelNewValueRequest{" +
                       "channelId=" + channelId +
                       '}';
    }
}
