package pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

public interface ChannelTypeDecoder {

    /**
     * @deprecated Use {@link ChannelTypeDecoder#decode(SuplaDeviceChannelB)}.
     */
    @Deprecated
    ChannelValue decode(SuplaDeviceChannel channel);

    ChannelValue decode(SuplaDeviceChannelB channel);
}
