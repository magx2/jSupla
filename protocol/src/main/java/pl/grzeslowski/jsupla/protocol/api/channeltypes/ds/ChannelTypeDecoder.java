package pl.grzeslowski.jsupla.protocol.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;

public interface ChannelTypeDecoder {

    /**
     * @deprecated Use {@link ChannelTypeDecoder#decode(SuplaDeviceChannelB)}.
     */
    @Deprecated
    ChannelValue decode(SuplaDeviceChannel channel);

    ChannelValue decode(SuplaDeviceChannelB channel);
}
