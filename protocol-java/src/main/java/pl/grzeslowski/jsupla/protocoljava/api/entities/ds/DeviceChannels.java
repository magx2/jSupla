package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;

@Deprecated
public class DeviceChannels implements Entity {
    @Size(max = SUPLA_CHANNELMAXCOUNT)
    private final Set<DeviceChannel> channels;

    public DeviceChannels(final @Size(max = SUPLA_CHANNELMAXCOUNT) Set<? extends DeviceChannel> channels) {
        this.channels = unmodifiableSet(new HashSet<>(sizeMax(channels, SUPLA_CHANNELMAXCOUNT)));
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

        return channels.equals(that.channels);
    }

    @Override
    public int hashCode() {
        return channels.hashCode();
    }

    @Override
    public String toString() {
        return "DeviceChannels{" +
                       "channels=" + channels +
                       '}';
    }
}
