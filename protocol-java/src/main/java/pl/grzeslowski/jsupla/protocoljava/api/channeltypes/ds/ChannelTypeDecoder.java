package pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

public interface ChannelTypeDecoder {

    /**
     * @deprecated Use {@link ChannelTypeDecoder#decode(SuplaDeviceChannelB)}.
     */
    @Deprecated
    ChannelValue decode(SuplaDeviceChannel channel);

    ChannelValue decode(SuplaDeviceChannelB channel);

    @Deprecated
    ChannelValue decode(SuplaChannelNewValue channel);

    ChannelValue decode(SuplaChannelNewValueB channel);

    ChannelValue decode(SuplaDeviceChannelValue supla);

    ChannelValue decode(pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue supla);

    ChannelValue decode(byte[] value);

    ChannelValue decodeNullable(byte[] any);
}
