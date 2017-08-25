package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import pl.grzeslowski.jsupla.Preconditions;

public final class RgbValue implements ChannelValue {
    public final int brightness;
    public final int colorBrightness;
    public final int r;
    public final int g;
    public final int b;

    public RgbValue(final int brightness, final int colorBrightness, final int r, final int g, final int b) {
        this.brightness = Preconditions.unsignedByteSize(brightness);
        this.colorBrightness = Preconditions.unsignedByteSize(colorBrightness);
        this.r = Preconditions.unsignedByteSize(r);
        this.g = Preconditions.unsignedByteSize(g);
        this.b = Preconditions.unsignedByteSize(b);
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
        if (r != rgbValue.r) {
            return false;
        }
        if (g != rgbValue.g) {
            return false;
        }
        return b == rgbValue.b;
    }

    @Override
    public int hashCode() {
        int result = brightness;
        result = 31 * result + colorBrightness;
        result = 31 * result + r;
        result = 31 * result + g;
        result = 31 * result + b;
        return result;
    }

    @Override
    public String toString() {
        return "RgbValue{" +
                       "brightness=" + brightness +
                       ", colorBrightness=" + colorBrightness +
                       ", R=" + r +
                       ", G=" + g +
                       ", B=" + b +
                       '}';
    }
}
