package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelBToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelToChannelType;

import java.util.Arrays;

public class SuplaDeviceChannelToChannelTypeImpl implements SuplaDeviceChannelToChannelType,
                                                                    SuplaDeviceChannelBToChannelType {
    @Override
    public ChannelType toChannelType(final SuplaDeviceChannelB suplaDeviceChannelB) {
        return findChannelType(suplaDeviceChannelB.type);
    }

    @Override
    @Deprecated
    public ChannelType toChannelType(final SuplaDeviceChannel suplaDeviceChannel) {
        return findChannelType(suplaDeviceChannel.type);
    }

    private ChannelType findChannelType(final int type) {
        return Arrays.stream(ChannelType.values())
                       .filter(x -> x.getValue() == type)
                       .findAny()
                       .orElse(ChannelType.UNKNOWN);
    }
}
