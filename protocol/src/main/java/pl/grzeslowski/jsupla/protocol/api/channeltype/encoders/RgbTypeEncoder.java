package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.RGBWValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.RGBWValue;

@Slf4j
public class RgbTypeEncoder implements ChannelValueEncoder<RgbValue> {
    @Override
    public void encode(RgbValue value, byte[] bytes) {
        var proto =
                new RGBWValue(
                        (byte) value.brightness(),
                        (byte) value.colorBrightness(),
                        (short) value.blue(),
                        (short) value.green(),
                        (short) value.red(),
                        (byte) value.subject().getValue(),
                        (byte) value.command().ordinal(),
                        (byte) value.dimmerCct());
        RGBWValueEncoder.INSTANCE.encode(proto, bytes, 0);
    }
}
