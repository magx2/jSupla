package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channeltypes;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
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

    @Override
    public ChannelValue decode(final SuplaChannelNewValue channel) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final SuplaChannelNewValueB channel) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final SuplaDeviceChannelValue supla) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue supla) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final byte[] value) {
        return null; // TODO
    }

    @Override
    public ChannelValue decodeNullable(final byte[] any) {
        return null; // TODO
    }
}
