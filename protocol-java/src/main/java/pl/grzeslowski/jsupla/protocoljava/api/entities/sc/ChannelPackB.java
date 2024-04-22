package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.B;

public class ChannelPackB extends ChannelPack {
    public ChannelPackB(
        @PositiveOrZero int totalLeft,
        @NotNull @Size(min = 1, max = SUPLA_CHANNELPACK_MAXSIZE) List<? extends ChannelB> channels) {
        super(totalLeft, channels);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends ChannelB> getChannels() {
        return (List<? extends ChannelB>) super.getChannels();
    }

    @Override
    public Version version() {
        return B;
    }
}
