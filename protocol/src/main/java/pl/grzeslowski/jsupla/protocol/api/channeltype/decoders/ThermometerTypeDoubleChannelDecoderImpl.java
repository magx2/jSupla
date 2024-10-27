package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

class ThermometerTypeDoubleChannelDecoderImpl implements Decoder<ChannelValue> {

    @Override
    public ChannelValue decode(final byte[] bytes, final int offset) {
        val temperature = INSTANCE.parseDouble(bytes, offset);
        return new TemperatureValue(temperature);
    }
}
