package pl.grzeslowski.jsupla.protocoljava.api.channelvalues;

public final class RgbValue implements ChannelValue {
    /**
     * unsigned.
     */
    public final short brightness;
    /**
     * unsigned.
     */
    public final short colorBrightness;
    /**
     * unsigned.
     */
    public final short red;
    /**
     * unsigned.
     */
    public final short green;
    /**
     * unsigned.
     */
    public final short blue;

    public RgbValue(final short brightness, final short colorBrightness,
                    final short red, final short green, final short blue) {
        this.brightness = brightness;
        this.colorBrightness = colorBrightness;
        this.red = red;
        this.green = green;
        this.blue = blue;
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
