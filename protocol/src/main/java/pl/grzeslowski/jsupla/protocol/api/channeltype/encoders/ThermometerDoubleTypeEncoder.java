package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureDoubleValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

public class ThermometerDoubleTypeEncoder implements ChannelValueEncoder<TemperatureDoubleValue> {

    @Override
    public void encode(TemperatureDoubleValue value, byte[] bytes) {
        final double temp = value.temperature().doubleValue();
        PrimitiveEncoder.INSTANCE.writeDouble(temp, bytes, 0);
    }
}
