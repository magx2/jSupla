package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.B;

public class DeviceChannelsB extends DeviceChannels {
    public DeviceChannelsB(final @Size(max = SUPLA_CHANNELMAXCOUNT) Set<? extends DeviceChannelB> channels) {
        super(channels);
    }

    @Override
    public Version version() {
        return B;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<? extends DeviceChannelB> getChannels() {
        return (Collection<? extends DeviceChannelB>) super.getChannels();
    }
}
