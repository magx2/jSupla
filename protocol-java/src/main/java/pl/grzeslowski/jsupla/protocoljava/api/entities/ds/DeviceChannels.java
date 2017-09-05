package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;

@Deprecated
public class DeviceChannels implements Entity {
    @Size(max = SUPLA_CHANNELMAXCOUNT)
    private final Collection<DeviceChannel> channels;

    public DeviceChannels(final @Size(max = SUPLA_CHANNELMAXCOUNT) Collection<? extends DeviceChannel> channels) {
        this.channels = unmodifiableList(new ArrayList<>(sizeMax(channels, SUPLA_CHANNELMAXCOUNT)));
    }

    public Collection<? extends DeviceChannel> getChannels() {
        return channels;
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
