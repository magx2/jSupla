package pl.grzeslowski.jsupla.server.entities.misc.ds;

import pl.grzeslowski.jsupla.server.entities.Entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;

public class DeviceChannels implements Entity {
    @NotNull
    @Size(max = SUPLA_CHANNELMAXCOUNT)
    private final List<? extends DeviceChannel> channels;

    public DeviceChannels(List<? extends DeviceChannel> channels) {
        this.channels = unmodifiableList(new ArrayList<>(sizeMax(channels, SUPLA_CHANNELMAXCOUNT)));
    }

    public DeviceChannel get(int i) {
        return channels.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceChannels)) {
            return false;
        }

        DeviceChannels that = (DeviceChannels) o;

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
