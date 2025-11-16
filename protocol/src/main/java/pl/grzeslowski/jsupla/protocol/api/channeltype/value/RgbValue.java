package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsignedByteSize;

/**
 * @param brightness
 * @param colorBrightness
 * @param red
 * @param green
 * @param blue
 */
public record RgbValue(int brightness, int colorBrightness, int red, int green, int blue)
        implements ChannelValue {
    public RgbValue {
        unsignedByteSize(brightness);
        unsignedByteSize(colorBrightness);
        unsignedByteSize(red);
        unsignedByteSize(green);
        unsignedByteSize(blue);
    }
}
