package pl.grzeslowski.jsupla.protocoljava.api.channels.values;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;

public final class RgbValue implements ChannelValue {
    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(255)
    public final int brightness;

    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(255)
    public final int colorBrightness;

    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(255)
    public final int red;

    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(255)
    public final int green;

    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(255)
    public final int blue;

    public RgbValue(final int brightness, final int colorBrightness,
                    final int red, final int green, final int blue) {
        this.brightness = unsignedByteSize(brightness);
        this.colorBrightness = unsignedByteSize(colorBrightness);
        this.red = unsignedByteSize(red);
        this.green = unsignedByteSize(green);
        this.blue = unsignedByteSize(blue);
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
