package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsignedByteSize;

import lombok.Value;

@Value
public class RgbValue implements ChannelValue {
    int brightness;
    int colorBrightness;
    int red;
    int green;
    int blue;

    public RgbValue(
            final int brightness,
            final int colorBrightness,
            final int red,
            final int green,
            final int blue) {
        this.brightness = unsignedByteSize(brightness);
        this.colorBrightness = unsignedByteSize(colorBrightness);
        this.red = unsignedByteSize(red);
        this.green = unsignedByteSize(green);
        this.blue = unsignedByteSize(blue);
    }
}
