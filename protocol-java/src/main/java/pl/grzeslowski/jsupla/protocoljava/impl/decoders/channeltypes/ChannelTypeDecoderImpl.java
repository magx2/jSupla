package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channeltypes;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

public class ChannelTypeDecoderImpl implements ChannelTypeDecoder {
    @Override
    public ChannelValue decode(final SuplaDeviceChannel channel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChannelValue decode(final SuplaDeviceChannelB channel) {
        throw new UnsupportedOperationException();
    }
}
