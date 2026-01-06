package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

abstract class DoubleTypeEncoder<ChannelValueT extends ChannelValue>
        implements ChannelValueEncoder<ChannelValueT> {
    @Override
    public final void encode(ChannelValueT value, byte[] bytes) {
        var doubleValue = mapToBigDecimal(value).doubleValue();
        PrimitiveEncoder.INSTANCE.writeDouble(doubleValue, bytes, 0);
    }

    protected abstract BigDecimal mapToBigDecimal(ChannelValueT value);
}
