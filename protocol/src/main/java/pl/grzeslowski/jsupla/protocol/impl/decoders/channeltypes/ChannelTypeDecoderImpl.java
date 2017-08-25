package pl.grzeslowski.jsupla.protocol.impl.decoders.channeltypes;

import pl.grzeslowski.jsupla.protocol.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocol.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;

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
