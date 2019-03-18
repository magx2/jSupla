package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class DeviceChannels {
    @Size(max = SUPLA_CHANNELMAXCOUNT)
    private final List<DeviceChannel> channels;

    public DeviceChannels(final @Size(max = SUPLA_CHANNELMAXCOUNT) List<? extends DeviceChannel> channels) {
        sizeMax(channels, SUPLA_CHANNELMAXCOUNT);
        this.channels = unmodifiableList(new ArrayList<>(channels));
    }

    public Collection<? extends DeviceChannel> getChannels() {
        return channels;
    }

    public int size() {
        return channels.size();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceChannels)) {
            return false;
        }

        final DeviceChannels that = (DeviceChannels) o;

        if (!that.canEqual(this)) {
            return false;
        }

        return channels.equals(that.channels);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DeviceChannels;
    }

    @Override
    public final int hashCode() {
        return channels.hashCode();
    }

    @Override
    public String toString() {
        return "DeviceChannels{" +
                "channels=" + channels +
                '}';
    }
}
