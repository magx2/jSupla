package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ELECTRICITY_METER;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.MeterValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.ChannelConfigGeneralPurposeMeterDecoder;

public class MeterValueDecoder implements ChannelValueDecoder<MeterValue> {
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_ELECTRICITY_METER);
    }

    @Override
    public Class<MeterValue> getChannelValueType() {
        return MeterValue.class;
    }

    @Override
    public MeterValue decode(byte[] bytes, int offset) {
        var decode = ChannelConfigGeneralPurposeMeterDecoder.INSTANCE.decode(bytes, offset);
        return null;
    }
}
