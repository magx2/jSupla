package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import pl.grzeslowski.jsupla.Preconditions;

public final class RgbValue implements ChannelValue {
    public final int brightness;
    public final int colorBrightness;
    public final int red;
    public final int green;
    public final int blue;

    public RgbValue(final int brightness, final int colorBrightness, final int red, final int green, final int blue) {
        this.brightness = Preconditions.unsignedByteSize(brightness);
        this.colorBrightness = Preconditions.unsignedByteSize(colorBrightness);
        this.red = Preconditions.unsignedByteSize(red);
        this.green = Preconditions.unsignedByteSize(green);
        this.blue = Preconditions.unsignedByteSize(blue);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RgbValue)) {
            return false;
        }

        final RgbValue rgbValue = (RgbValue) o;

        if (brightness != rgbValue.brightness) {
            return false;
        }
        if (colorBrightness != rgbValue.colorBrightness) {
            return false;
        }
        if (red != rgbValue.red) {
            return false;
        }
        if (green != rgbValue.green) {
            return false;
        }
        return blue == rgbValue.blue;
    }

    @Override
    public int hashCode() {
        int result = brightness;
        result = 31 * result + colorBrightness;
        result = 31 * result + red;
        result = 31 * result + green;
        result = 31 * result + blue;
        return result;
    }

    @Override
    public String toString() {
        return "RgbValue{" +
                       "brightness=" + brightness +
                       ", colorBrightness=" + colorBrightness +
                       ", R=" + red +
                       ", G=" + green +
                       ", B=" + blue +
                       '}';
    }
}
